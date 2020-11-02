package org.jabref.gui.wordcloud;

import java.util.Optional;

import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.ActionHelper;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.logic.l10n.Localization;
import org.jabref.preferences.JabRefPreferences;

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
    }
}
