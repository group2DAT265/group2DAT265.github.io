package org.jabref.gui.webofscience;

import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.ActionHelper;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.logic.l10n.Localization;

public class WebOfScienceAction extends SimpleCommand {

    private final DialogService dialogService;
    private final StateManager stateManager;
    private final JabRefFrame jabRefFrame;

    public WebOfScienceAction(JabRefFrame jabRefFrame, DialogService dialogService, StateManager stateManager) {
        this.dialogService = dialogService;
        this.stateManager = stateManager;
        this.jabRefFrame = jabRefFrame;

        this.executable.bind(ActionHelper.needsEntriesSelected(stateManager));

    }

    @Override
    public void execute() {
        if (stateManager.getActiveDatabase().isEmpty()) {
            dialogService.showInformationDialogAndWait(Localization.lang("Web of Science"), Localization.lang("An API or library for citation bar graph"));
            return;
        }

        if (stateManager.getSelectedEntries().isEmpty()) { // None selected. Inform the user to select entries first.
            dialogService.showInformationDialogAndWait(Localization.lang("Web of Science"), Localization.lang("First select entries to generate citation graph"));
            return;
        }

        dialogService.notify(Localization.lang("Generating a citation graph using %0 selected entries...", Integer.toString(stateManager.getSelectedEntries().size())));

        // TODO: Implementation of web of science dialog view
    }
}
