package org.jabref.model.entrystatistics;

import java.util.HashMap;
import java.util.TreeMap;

public class EntryStatistics {
    private TreeMap<String, Integer> citationReport;
    private HashMap<String, Integer> yearPubReport;
    private HashMap<String, Integer> authCountReport;

    public EntryStatistics() {
    }

    public void setCitationReport(TreeMap<String, Integer> citationReport) {
        this.citationReport = citationReport;
    }

    public TreeMap<String, Integer> getCitationReport() {
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
