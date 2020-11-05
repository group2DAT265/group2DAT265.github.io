package org.jabref.gui.wordcloud;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import javafx.collections.ObservableList;
import org.jabref.gui.DialogService;
import org.jabref.gui.Globals;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.ActionHelper;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.gui.util.FileDialogConfiguration;
import org.jabref.logic.l10n.Localization;
import org.jabref.logic.util.StandardFileType;
import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.wordcloud.WordCloudPreset;
import org.jabref.preferences.JabRefPreferences;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/*
 * Action used when generating a wordcloud from selected entries
 * The button that uses this action can only be clicked when a user has selected at least one entry.
 *
 */

public class GenerateWordCloudAction extends SimpleCommand {

    private final DialogService dialogService;
    private final StateManager stateManager;
    private final JabRefFrame jabRefFrame;
    private final JabRefPreferences preferences;

    public GenerateWordCloudAction(JabRefFrame jabRefFrame, DialogService dialogService, StateManager stateManager) {
        this.dialogService = dialogService;
        this.stateManager = stateManager;
        this.jabRefFrame = jabRefFrame;
        this.preferences = Globals.prefs;

        this.executable.bind(ActionHelper.needsEntriesSelected(stateManager));

    }

    @Override
    public void execute() {
        if (stateManager.getActiveDatabase().isEmpty()) {
            dialogService.showInformationDialogAndWait(Localization.lang("Generate wordcloud"), Localization.lang("You need a library with entries to generate a wordcloud."));
            return;
        }

        if (stateManager.getSelectedEntries().isEmpty()) { // None selected. Inform the user to select entries first.
            dialogService.showInformationDialogAndWait(Localization.lang("Generate wordcloud"), Localization.lang("First select entries to generate wordcloud from."));
            return;
        }

        // TODO: Implement generation and export of wordcloud using wordcloudPreferences and selected entries in stateManager
        Optional<WordCloudPreset> chosenPreset = new WordCloudPresetView(jabRefFrame.getCurrentBasePanel(), dialogService).showAndWait();

        chosenPreset.ifPresent(wordCloudPreset -> {
            try {
                generateWordCloud(wordCloudPreset);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void generateWordCloud(WordCloudPreset wordCloudPreset) throws IOException {

        List<String> stopWords = Files.readAllLines(Paths.get("./src/main/resources/wordcloud/stopwords.txt"));
        ObservableList<BibEntry> listOfSelectedEntries = stateManager.getSelectedEntries();
        BibDatabase activeDatabase = stateManager.getActiveDatabase().get().getDatabase();
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("test", ".txt");
            FileWriter writer = new FileWriter(tmpFile);



            listOfSelectedEntries.forEach((entry) -> {
                if(entry.hasField(StandardField.ABSTRACT)){
                    String abstractWords = entry.getField(StandardField.ABSTRACT).get();
                    System.out.println("Abstract: "+abstractWords);
                    ArrayList<String> allWords =
                            Stream.of(abstractWords.toLowerCase().split(" "))
                                    .collect(Collectors.toCollection(ArrayList<String>::new));
                    allWords.removeAll(stopWords);

                    abstractWords = allWords.stream().collect(Collectors.joining(" "));
                    try {
                        writer.write(abstractWords);
                        writer.write(" ");
                    } catch (IOException e) {
                        System.out.println("Could not write to tmp abstract file.");
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("Entry has no abstract.");
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not create text file for abstracts.");
            e.printStackTrace();
        }

        if(tmpFile != null){
            // This is currently just an example. We need to use the preferences from the dialog and the selected entries instead
            final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
            final Dimension dimension = new Dimension(600, 600);

            final WordCloud wordCloud;
            if(wordCloudPreset.getShape() == "Rectangle"){
                wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
                wordCloud.setBackground(new RectangleBackground(dimension));
                frequencyAnalyzer.setWordFrequenciesToReturn(300);
            }else{
                wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
                wordCloud.setBackground(new CircleBackground(300));
                frequencyAnalyzer.setWordFrequenciesToReturn(150);
            }
            final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(tmpFile);

            wordCloud.setPadding(1);
            wordCloud.setBackgroundColor(wordCloudPreset.getBackground());
            wordCloud.setColorPalette(wordCloudPreset.getColors());
            wordCloud.setKumoFont(wordCloudPreset.getFont());
            wordCloud.setFontScalar(new LinearFontScalar(10, 40));
            wordCloud.build(wordFrequencies);
            wordCloud.writeToFile("./src/main/resources/wordcloud/generated_wordcloud.png");
            Optional<WordCloudPreset> downloadOrNot = new WordCloudGeneratedView(jabRefFrame.getCurrentBasePanel(), dialogService).showAndWait();
            downloadOrNot.ifPresent(preset -> {
                FileDialogConfiguration fileDialogConfiguration = new FileDialogConfiguration.Builder()
                        .addExtensionFilter(StandardFileType.PNG)
                        .withDefaultExtension(StandardFileType.PNG)
                        .withInitialDirectory(preferences.get(JabRefPreferences.WORKING_DIRECTORY))
                        .build();
                Optional<Path> selectedPath = dialogService.showFileSaveDialog(fileDialogConfiguration);
                selectedPath.ifPresent(path -> {
                    wordCloud.writeToFile(path.toString());
                });

                System.out.println(selectedPath);
            });

            //dialogService.notify(Localization.lang("Word cloud generated!"));


        }else{
            System.out.println("The tmp file for abstracts is null.");
        }



    }
}
