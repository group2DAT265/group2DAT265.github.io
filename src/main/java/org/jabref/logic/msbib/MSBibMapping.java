package org.jabref.logic.msbib;

import java.util.HashMap;
import java.util.Map;

import org.jabref.model.entry.field.Field;
import org.jabref.model.entry.field.InternalField;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.field.UnknownField;
import org.jabref.model.entry.types.EntryType;
import org.jabref.model.entry.types.IEEETranEntryType;
import org.jabref.model.entry.types.StandardEntryType;

import com.google.common.collect.HashBiMap;

/**
 * Mapping between Msbib and biblatex All Fields: <a href = "https://msdn.microsoft.com/de-de/library/office/documentformat.openxml.bibliography">List
 * of all MSBib fields</a>
 */
public class MSBibMapping {

    private static final String BIBTEX_PREFIX = "BIBTEX_";
    private static final String MSBIB_PREFIX = "msbib-";

    private static final HashBiMap<Field, String> BIBLATEX_TO_MS_BIB = HashBiMap.create();

    static {
        BIBLATEX_TO_MS_BIB.put(InternalField.KEY_FIELD, "Tag");
        BIBLATEX_TO_MS_BIB.put(StandardField.TITLE, "Title");
        BIBLATEX_TO_MS_BIB.put(StandardField.YEAR, "Year");
        BIBLATEX_TO_MS_BIB.put(StandardField.VOLUME, "Volume");
        BIBLATEX_TO_MS_BIB.put(StandardField.LANGUAGE, "LCID");
        BIBLATEX_TO_MS_BIB.put(StandardField.EDITION, "Edition");
        BIBLATEX_TO_MS_BIB.put(StandardField.PUBLISHER, "Publisher");
        BIBLATEX_TO_MS_BIB.put(StandardField.BOOKTITLE, "BookTitle");
        BIBLATEX_TO_MS_BIB.put(StandardField.SHORTTITLE, "ShortTitle");
        BIBLATEX_TO_MS_BIB.put(StandardField.NOTE, "Comments");
        BIBLATEX_TO_MS_BIB.put(StandardField.VOLUMES, "NumberVolumes");

        BIBLATEX_TO_MS_BIB.put(StandardField.CHAPTER, "ChapterNumber");

        BIBLATEX_TO_MS_BIB.put(StandardField.ISSUE, "Issue");
        BIBLATEX_TO_MS_BIB.put(StandardField.SCHOOL, "Department");
        BIBLATEX_TO_MS_BIB.put(StandardField.INSTITUTION, "Institution");
        BIBLATEX_TO_MS_BIB.put(StandardField.DOI, "DOI");
        BIBLATEX_TO_MS_BIB.put(StandardField.URL, "URL");
        // BibTeX/Biblatex only fields

        BIBLATEX_TO_MS_BIB.put(StandardField.SERIES, BIBTEX_PREFIX + "Series");
        BIBLATEX_TO_MS_BIB.put(StandardField.ABSTRACT, BIBTEX_PREFIX + "Abstract");
        BIBLATEX_TO_MS_BIB.put(StandardField.KEYWORDS, BIBTEX_PREFIX + "KeyWords");
        BIBLATEX_TO_MS_BIB.put(StandardField.CROSSREF, BIBTEX_PREFIX + "CrossRef");
        BIBLATEX_TO_MS_BIB.put(StandardField.HOWPUBLISHED, BIBTEX_PREFIX + "HowPublished");
        BIBLATEX_TO_MS_BIB.put(StandardField.PUBSTATE, BIBTEX_PREFIX + "Pubstate");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("affiliation"), BIBTEX_PREFIX + "Affiliation");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("contents"), BIBTEX_PREFIX + "Contents");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("copyright"), BIBTEX_PREFIX + "Copyright");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("price"), BIBTEX_PREFIX + "Price");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("size"), BIBTEX_PREFIX + "Size");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("intype"), BIBTEX_PREFIX + "InType");
        BIBLATEX_TO_MS_BIB.put(new UnknownField("paper"), BIBTEX_PREFIX + "Paper");
        BIBLATEX_TO_MS_BIB.put(StandardField.KEY, BIBTEX_PREFIX + "Key");

        // MSBib only fields
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "periodical"), "PeriodicalTitle");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + StandardField.DAY), "Day");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "accessed"), "Accessed");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "medium"), "Medium");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "recordingnumber"), "RecordingNumber");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "theater"), "Theater");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "distributor"), "Distributor");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "broadcaster"), "Broadcaster");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "station"), "Station");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + StandardField.TYPE), "Type");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "court"), "Court");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "reporter"), "Reporter");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "casenumber"), "CaseNumber");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "abbreviatedcasenumber"), "AbbreviatedCaseNumber");
        BIBLATEX_TO_MS_BIB.put(new UnknownField(MSBIB_PREFIX + "productioncompany"), "ProductionCompany");
    }

    private MSBibMapping() {
    }

    public static EntryType getBiblatexEntryType(String msbibType) {
        Map<String, EntryType> entryTypeMapping = new HashMap<>();

        entryTypeMapping.put("Book", StandardEntryType.BOOK);
        entryTypeMapping.put("BookSection", StandardEntryType.BOOK);
        entryTypeMapping.put("JournalArticle", StandardEntryType.ARTICLE);
        entryTypeMapping.put("ArticleInAPeriodical", IEEETranEntryType.PERIODICAL);
        entryTypeMapping.put("ConferenceProceedings", StandardEntryType.IN_PROCEEDINGS);
        entryTypeMapping.put("Report", StandardEntryType.TECH_REPORT);
        entryTypeMapping.put("Patent", IEEETranEntryType.PATENT);
        entryTypeMapping.put("InternetSite", StandardEntryType.ON_LINE);

        return entryTypeMapping.getOrDefault(msbibType, StandardEntryType.MISC);
    }

    public static MSBibEntryType getMSBibEntryType(EntryType bibtexType) {
        Map<EntryType, MSBibEntryType> entryTypeMapping = new HashMap<>();

        entryTypeMapping.put(StandardEntryType.BOOK, MSBibEntryType.BOOK);
        entryTypeMapping.put(StandardEntryType.IN_BOOK, MSBibEntryType.BOOK_SECTION);
        entryTypeMapping.put(StandardEntryType.BOOKLET, MSBibEntryType.BOOK_SECTION);
        entryTypeMapping.put(StandardEntryType.IN_COLLECTION, MSBibEntryType.BOOK_SECTION);
        entryTypeMapping.put(StandardEntryType.ARTICLE, MSBibEntryType.JOURNAL_ARTICLE);
        entryTypeMapping.put(StandardEntryType.IN_PROCEEDINGS, MSBibEntryType.CONFERENCE_PROCEEDINGS);
        entryTypeMapping.put(StandardEntryType.CONFERENCE, MSBibEntryType.CONFERENCE_PROCEEDINGS);
        entryTypeMapping.put(StandardEntryType.PROCEEDINGS, MSBibEntryType.CONFERENCE_PROCEEDINGS);
        entryTypeMapping.put(StandardEntryType.COLLECTION, MSBibEntryType.CONFERENCE_PROCEEDINGS);
        entryTypeMapping.put(StandardEntryType.TECH_REPORT, MSBibEntryType.REPORT);
        entryTypeMapping.put(StandardEntryType.MANUAL, MSBibEntryType.REPORT);
        entryTypeMapping.put(StandardEntryType.MASTER_THESIS, MSBibEntryType.REPORT);
        entryTypeMapping.put(StandardEntryType.PHD_THESIS, MSBibEntryType.REPORT);
        entryTypeMapping.put(StandardEntryType.UNPUBLISHED, MSBibEntryType.REPORT);
        entryTypeMapping.put(IEEETranEntryType.PATENT, MSBibEntryType.PATENT);
        entryTypeMapping.put(StandardEntryType.MISC, MSBibEntryType.MISC);
        entryTypeMapping.put(IEEETranEntryType.ELECTRONIC, MSBibEntryType.ELECTRONIC_SOURCE);
        entryTypeMapping.put(StandardEntryType.ON_LINE, MSBibEntryType.INTERNET_SITE);

        return entryTypeMapping.getOrDefault(bibtexType, MSBibEntryType.MISC);
    }

    /**
     * Only English is supported <br>
     * <a href="http://www.microsoft.com/globaldev/reference/lcid-all.mspx">All LCID codes</a>
     *
     * @param language The language to transform
     * @return Returns 0 for English
     */
    public static int getLCID(String language) {
        // TODO: add language to LCID mapping
        // 0x0409 is American English
        return 0x0409;
    }

    /**
     * Only English is supported <br>
     * <a href="http://www.microsoft.com/globaldev/reference/lcid-all.mspx">All LCID codes</a>
     *
     * @return Returns english
     */
    public static String getLanguage(int LCID) {
        // TODO: add language to LCID mapping
        return "english";
    }

    public static String getMSBibField(Field field) {
        return BIBLATEX_TO_MS_BIB.get(field);
    }

    public static Field getBibTeXField(String msbibFieldName) {
        return BIBLATEX_TO_MS_BIB.inverse().get(msbibFieldName);
    }
}
