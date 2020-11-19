package org.jabref.gui.entrystatistics;

import com.microsoft.applicationinsights.core.dependencies.http.HttpException;
import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.ActionHelper;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.logic.importer.fetcher.SemanticScholar;
import org.jabref.logic.importer.fileformat.bibtexml.Entry;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entrystatistics.EntryStatistics;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class EntryStatisticsAction extends SimpleCommand {

    private final DialogService dialogService;
    private final StateManager stateManager;
    private final JabRefFrame jabRefFrame;

    public EntryStatisticsAction(JabRefFrame jabRefFrame, DialogService dialogService, StateManager stateManager) {
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

        EntryStatistics entryStatistics = new EntryStatistics();

        ArrayList<String> selectedEntriesDOI = getDOIsFromSelectedEntries();
        try {
            HashMap<String, Integer> citationReport = SemanticScholar.getCitationReportsByDOIs(selectedEntriesDOI);
            entryStatistics.setCitationReport(citationReport);
        } catch (IOException | InterruptedException | HttpException e) {
            e.printStackTrace();
        }

        // TODO: Add remaining stuff

        EntryStatisticsView view = new EntryStatisticsView(jabRefFrame.getCurrentBasePanel(), dialogService);
        view.setStatistics(entryStatistics);
        view.showAndWait();

    }

    private ArrayList<String> getDOIsFromSelectedEntries() {
        ArrayList<String> dois = new ArrayList<>();

        stateManager.getSelectedEntries().forEach(bibEntry -> {
            bibEntry.getDOI().ifPresent(doi -> dois.add(doi.getDOI()));
        });

        return dois;
    }
}
