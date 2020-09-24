package org.jabref.model.entry.types;

import java.util.Locale;

public enum StandardEntryType implements EntryType {
    // BibTeX
    ARTICLE("Article"),
    BOOK("Book"),
    BOOKLET("Booklet"),
    COLLECTION("Collection"),
    CONFERENCE("Conference"),
    IN_BOOK("InBook"),
    IN_COLLECTION("InCollection"),
    IN_PROCEEDINGS("InProceedings"),
    MANUAL("Manual"),
    MASTER_THESIS("MastersThesis"),
    MISC("Misc"),
    PHD_THESIS("PhdThesis"),
    PROCEEDINGS("Proceedings"),
    TECH_REPORT("TechReport"),
    UNPUBLISHED("Unpublished"),
    // Biblatex
    BOOK_IN_BOOK("BookInBook"),
    IN_REFERENCE("InReference"),
    MV_BOOK("MvBook"),
    MV_COLLECTION("MvCollection"),
    MV_PROCEEDINGS("MvProceedings"),
    MV_REFERENCE("MvReference"),
    ON_LINE("Online"),
    REFERENCE("Reference"),
    REPORT("Report"),
    SET("Set"),
    SUPP_BOOK("SuppBook"),
    SUPP_COLLECTION("SuppCollection"),
    SUPP_PERIODICAL("SuppPeriodical"),
    THESIS("Thesis"),
    WWW("WWW"),
    SOFTWARE("Software"),
    DATASET("Dataset"),
    SOFTWARE_VERSION("SoftwareVersion"),
    SOFTWARE_MODULE("SoftwareModule"),
    CODE_FRAGMENT("CodeFragment");

    private final String displayName;

    StandardEntryType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getName() {
        return displayName.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
