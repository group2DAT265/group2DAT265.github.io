package org.jabref.gui.wordcloud;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.ActionHelper;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.logic.l10n.Localization;

import java.awt.*;
import java.io.IOException;
import java.util.List;


/*
 * Action used when generating a wordcloud from selected entries
 * The button that uses this action can only be clicked when a user has selected at least one entry.
 *
 */

public class GenerateWordCloudAction extends SimpleCommand {

    private final DialogService dialogService;
    private final StateManager stateManager;
    private final JabRefFrame jabRefFrame;

    public GenerateWordCloudAction(JabRefFrame jabRefFrame, DialogService dialogService, StateManager stateManager) {
        this.dialogService = dialogService;
        this.stateManager = stateManager;
        this.jabRefFrame = jabRefFrame;

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



        // the @{wordcouldPreferences} should include the preferences that the user sets in the dialog of how the wordcloud should be generated
//        Optional<Void> wordcloudPreferences = new WordCloudDialog().showAndWait();

        dialogService.notify(Localization.lang("Generating a wordcloud using %0 selected entries...", Integer.toString(stateManager.getSelectedEntries().size())));

        // TODO: Implement generation and export of wordcloud using wordcloudPreferences and selected entries in stateManager
        WordCloudView view = new WordCloudView(jabRefFrame.getCurrentBasePanel(), dialogService);
//        jabRefFrame.getCurrentBasePanel().inser
        view.showAndWait();


        try {
            generateWordCloud();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateWordCloud() throws IOException {

        // This is currently just an example. We need to use the preferences from the dialog and the selected entries instead
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load("./src/main/resources/wordcloud/text.txt");
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
        wordCloud.setPadding(0);
        wordCloud.setBackgroundColor(Color.WHITE);
        wordCloud.setBackground(new RectangleBackground(dimension));
        wordCloud.setColorPalette(new ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE));
        wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("./src/main/resources/wordcloud/wordcloud_rectangle.png");

    }
}
