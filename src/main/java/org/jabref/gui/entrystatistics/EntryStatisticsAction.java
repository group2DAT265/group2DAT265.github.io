package org.jabref.gui.entrystatistics;

import com.microsoft.applicationinsights.core.dependencies.http.HttpException;
import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.ActionHelper;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.logic.importer.fetcher.SemanticScholar;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entrystatistics.EntryStatistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;

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

        entryStatistics.setAuthCountReport(generateAuthorStats());
        entryStatistics.setYearPubReport(generatePubYearStats());

        EntryStatisticsView view = new EntryStatisticsView(jabRefFrame.getCurrentBasePanel(), dialogService);
        view.setStatistics(entryStatistics);
        view.showAndWait();

    }

    private HashMap<String, Integer> generateAuthorStats() {

        ObservableList<BibEntry> listOfSelectedEntries = stateManager.getSelectedEntries();

        HashMap<String, Integer> authorOccurrences = new HashMap<>();

        for (BibEntry entry : listOfSelectedEntries) {
            if (entry.hasField(StandardField.AUTHOR)) {
                String auths = entry.getField(StandardField.AUTHOR).get();
                if (auths != null) {
                    String[] splitAuths = auths.split(" and ");
                    for (String auth : splitAuths) {
                        auth.trim();
                        if (authorOccurrences.containsKey(auth)) {
                            authorOccurrences.put(auth, authorOccurrences.get(auth) + 1);
                        } else {
                            authorOccurrences.put(auth, 1);
                        }
                    }
                }
            }
        }
        return authorOccurrences;
    }

    private HashMap<String, Integer> generatePubYearStats() {
        ObservableList<BibEntry> listOfSelectedEntries = stateManager.getSelectedEntries();

        HashMap<String, Integer> yearOccurrances = new HashMap<>();
        for (BibEntry entry : listOfSelectedEntries) {
            if (entry.hasField(StandardField.YEAR)) {
                String year = entry.getField(StandardField.YEAR).get();
                if (year != null) {
                    if (yearOccurrances.containsKey(year)) {
                        yearOccurrances.put(year, yearOccurrances.get(year) + 1);
                    } else {
                        yearOccurrances.put(year, 1);
                    }
                }
            }
        }
        return yearOccurrances;
    }

    private ArrayList<String> getDOIsFromSelectedEntries() {
        ArrayList<String> dois = new ArrayList<>();

        stateManager.getSelectedEntries().forEach(bibEntry -> {
            bibEntry.getDOI().ifPresent(doi -> dois.add(doi.getDOI()));
        });

        return dois;
    }
}
