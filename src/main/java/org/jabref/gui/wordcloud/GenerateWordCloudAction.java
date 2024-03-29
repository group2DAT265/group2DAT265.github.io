package org.jabref.gui.wordcloud;

import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.ObservableList;

import org.jabref.architecture.AllowedToUseAwt;
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

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.image.AngleGenerator;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

/*
 * Action used when generating a wordcloud from selected entries
 * The button that uses this action can only be clicked when a user has selected at least one entry.
 *
 */

@AllowedToUseAwt("Requires AWT to create word cloud")
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

        // Opens dialog to collects user's preferences for generation of a word cloud
        Optional<WordCloudPreset> chosenPreset = new WordCloudPresetView(jabRefFrame.getCurrentBasePanel(), dialogService).showAndWait();

        chosenPreset.ifPresent(wordCloudPreset -> {
            try {
                generateWordCloud(wordCloudPreset);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void generatePublishedYear() {

        ObservableList<BibEntry> listOfSelectedEntries = stateManager.getSelectedEntries();

        HashMap<String, Integer> authorOccurrances = new HashMap<>();

        Iterator<BibEntry> it = listOfSelectedEntries.iterator();

        while (it.hasNext()) {
            BibEntry entry = it.next();
            if (entry.hasField(StandardField.AUTHOR)) {
                String auths = entry.getField(StandardField.AUTHOR).get();
                if (auths != null) {

                }
            }
        }
    }

    public void generateWordCloud(WordCloudPreset wordCloudPreset) throws IOException {
        // Reads file with stopword, words to be removed, to be able to remove all those words from the abstracts before word cloud generation
        List<String> stopWords = Files.readAllLines(Path.of("./src/main/resources/wordcloud/stopwords.txt"));

        // Collect the abstracts of the selected entries, reads all the abstracts, removes stopwords and puts them in a temp file to be used for wordcloud generation
        ObservableList<BibEntry> listOfSelectedEntries = stateManager.getSelectedEntries();
        BibDatabase activeDatabase = stateManager.getActiveDatabase().get().getDatabase();
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("test", ".txt");
            FileWriter writer = new FileWriter(tmpFile);
            listOfSelectedEntries.forEach((entry) -> {
                String words = "";
                if (((wordCloudPreset.getContent() == "Abstract") || (wordCloudPreset.getContent() == "All")) && entry.hasField(StandardField.ABSTRACT)) {
                    words += entry.getField(StandardField.ABSTRACT).get();
                } else if (((wordCloudPreset.getContent() == "Title") || (wordCloudPreset.getContent() == "All")) && entry.hasField(StandardField.TITLE)) {
                    words += entry.getField(StandardField.TITLE).get();
                } else if (((wordCloudPreset.getContent() == "Keywords") || (wordCloudPreset.getContent() == "All")) && entry.hasField(StandardField.KEYWORDS)) {
                    words += entry.getField(StandardField.KEYWORDS).get();
                }

                ArrayList<String> allWords =
                        Stream.of(words.toLowerCase().split(" "))
                                .collect(Collectors.toCollection(ArrayList<String>::new));
                allWords.removeAll(stopWords);

                words = allWords.stream().collect(Collectors.joining(" "));
                try {
                    writer.write(words);
                    writer.write(" ");
                } catch (IOException e) {
                    System.out.println("Could not write to tmp abstract file.");
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not create text file for abstracts.");
            e.printStackTrace();
        }

        if (tmpFile != null) {
            // Generation of word cloud
            final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
            final Dimension dimension = new Dimension(600, 600);

            // Rectangle or circle as the background
            final WordCloud wordCloud;
            if (wordCloudPreset.getShape() == "Rectangle") {
                wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
                wordCloud.setBackground(new RectangleBackground(dimension));
            } else {
                wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
                wordCloud.setBackground(new CircleBackground(300));
            }
            if (wordCloudPreset.getDirection() == "Horizontal") {
                wordCloud.setAngleGenerator(new AngleGenerator(0));
                // wordCloud.setAngleGenerator(new AngleGenerator(0,0.01,2));
            }
            frequencyAnalyzer.setWordFrequenciesToReturn(wordCloudPreset.getNumberOfWords());
            final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(tmpFile);

            wordCloud.setPadding(1);
            wordCloud.setBackgroundColor(wordCloudPreset.getBackground());
            wordCloud.setColorPalette(wordCloudPreset.getColors());
            wordCloud.setKumoFont(wordCloudPreset.getFont());
            wordCloud.setFontScalar(new LinearFontScalar(10, 50));
            wordCloud.build(wordFrequencies);
            // Temp save of word-cloud
            wordCloud.writeToFile("./src/main/resources/wordcloud/generated_wordcloud.png");

            // Opens a dialog with a preview of the word-cloud and the option to download or cancel
            Optional<WordCloudPreset> downloadOrNot = new WordCloudGeneratedView(jabRefFrame.getCurrentBasePanel(), dialogService).showAndWait();
            downloadOrNot.ifPresent(preset -> {
                FileDialogConfiguration fileDialogConfiguration = new FileDialogConfiguration.Builder()
                        .addExtensionFilter(StandardFileType.PNG)
                        .withDefaultExtension(StandardFileType.PNG)
                        .withInitialDirectory(preferences.get(JabRefPreferences.WORKING_DIRECTORY))
                        .build();

                // Collect the path that the user wants the image saved to
                Optional<Path> selectedPath = dialogService.showFileSaveDialog(fileDialogConfiguration);
                selectedPath.ifPresent(path -> {
                    wordCloud.writeToFile(path.toString());
                });

                dialogService.notify(Localization.lang("Word cloud saved!"));
            });
        } else {
            System.out.println("The tmp file for abstracts is null.");
        }
    }
}
