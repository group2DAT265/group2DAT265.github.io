package org.jabref.logic.msbib;

/**
 * This class represents all supported MSBib entry types.
 * <p>
 * Book, BookSection, JournalArticle, ArticleInAPeriodical, ConferenceProceedings, Report,
 * InternetSite, DocumentFromInternetSite, ElectronicSource, Art, SoundRecording, Performance,
 * Film, Interview, Patent, Case, Misc
 *
 * See BIBFORM.XML, shared-bibliography.xsd (ECMA standard)
 */
public enum MSBibEntryType {
    ARTICLE_IN_A_PERIODICAL,
    BOOK,
    BOOK_SECTION,
    JOURNAL_ARTICLE,
    CONFERENCE_PROCEEDINGS,
    REPORT,
    SOUND_RECORDING,
    PERFORMANCE,
    ART,
    DOCUMENT_FROM_INTERNET_SITE,
    INTERNET_SITE,
    FILM,
    INTERVIEW,
    PATENT,
    ELECTRONIC_SOURCE,
    CASE,
    MISC
}
