package org.jabref.model.entrystatistics;

import java.util.HashMap;

public class EntryStatistics {
    // TODO: CitationReport
    private HashMap<String, Integer> citationReport;

    public EntryStatistics() {
    }

    public void setCitationReport(HashMap<String, Integer> citationReport) {
        this.citationReport = citationReport;
    }

    public HashMap<String, Integer> getCitationReport() {
        return this.citationReport;
    }
}
