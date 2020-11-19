package org.jabref.model.entrystatistics;

import java.util.HashMap;

public class EntryStatistics {
    // TODO: CitationReport
    private HashMap<String, Integer> citationReport;
    private HashMap<String, Integer> yearPubReport;
    private HashMap<String, Integer> authCountReport;

    public EntryStatistics() {
    }

    public void setCitationReport(HashMap<String, Integer> citationReport) {
        this.citationReport = citationReport;
    }

    public HashMap<String, Integer> getCitationReport() {
        return this.citationReport;
    }

    public void setYearPubReport(HashMap<String, Integer> yearPubReport) {
        this.yearPubReport = yearPubReport;
    }

    public HashMap<String, Integer> getYearPubReport() {
        return this.yearPubReport;
    }

    public void setAuthCountReport(HashMap<String, Integer> authCountReport) {
        this.authCountReport = authCountReport;
    }

    public HashMap<String, Integer> getAuthCountReport() {
        return this.authCountReport;
    }
}
