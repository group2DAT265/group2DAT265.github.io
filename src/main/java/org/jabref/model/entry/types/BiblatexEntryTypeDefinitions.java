package org.jabref.model.entry.types;

import java.util.Arrays;
import java.util.List;

import org.jabref.model.entry.BibEntryType;
import org.jabref.model.entry.BibEntryTypeBuilder;
import org.jabref.model.entry.field.OrFields;
import org.jabref.model.entry.field.StandardField;

/**
 * This class defines entry types for biblatex support.
 *
 * @see <a href="http://mirrors.concertpass.com/tex-archive/macros/latex/contrib/biblatex/doc/biblatex.pdf">biblatex documentation</a>
 */
public class BiblatexEntryTypeDefinitions {

    private static final BibEntryType ARTICLE = new BibEntryTypeBuilder()
            .withType(StandardEntryType.ARTICLE)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.EDITOR, StandardField.SERIES, StandardField.VOLUME, StandardField.NUMBER,
                    StandardField.EID, StandardField.ISSUE, StandardField.PAGES, StandardField.NOTE, StandardField.ISSN, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withDetailFields(
                    StandardField.TRANSLATOR, StandardField.ANNOTATOR, StandardField.COMMENTATOR, StandardField.SUBTITLE,
                    StandardField.TITLEADDON, StandardField.EDITOR, StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC,
                    StandardField.JOURNALSUBTITLE, StandardField.ISSUETITLE, StandardField.ISSUESUBTITLE, StandardField.LANGUAGE,
                    StandardField.ORIGLANGUAGE, StandardField.SERIES, StandardField.VOLUME, StandardField.NUMBER, StandardField.EID,
                    StandardField.ISSUE, StandardField.PAGES, StandardField.VERSION, StandardField.NOTE,
                    StandardField.ISSN, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(
                    StandardField.AUTHOR, StandardField.TITLE, StandardField.JOURNALTITLE, StandardField.DATE)
            .build();

    private static final BibEntryType BOOK = new BibEntryTypeBuilder()
            .withType(StandardEntryType.BOOK)
            .withImportantFields(StandardField.EDITOR,
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE, StandardField.MAINSUBTITLE,
                    StandardField.MAINTITLEADDON, StandardField.VOLUME, StandardField.EDITION, StandardField.PUBLISHER, StandardField.ISBN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITOR, StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC,
                    StandardField.TRANSLATOR, StandardField.ANNOTATOR, StandardField.COMMENTATOR, StandardField.INTRODUCTION,
                    StandardField.FOREWORD, StandardField.AFTERWORD, StandardField.SUBTITLE, StandardField.TITLEADDON,
                    StandardField.MAINTITLE, StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.LANGUAGE,
                    StandardField.ORIGLANGUAGE, StandardField.VOLUME, StandardField.PART, StandardField.EDITION, StandardField.VOLUMES,
                    StandardField.SERIES, StandardField.NUMBER, StandardField.NOTE, StandardField.PUBLISHER, StandardField.LOCATION,
                    StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.ADDENDUM,
                    StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE,
                    StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType MVBOOK = new BibEntryTypeBuilder()
            .withType(StandardEntryType.MV_BOOK)
            .withImportantFields(StandardField.EDITOR, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.EDITION,
                    StandardField.PUBLISHER, StandardField.ISBN, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITOR, StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC,
                    StandardField.TRANSLATOR, StandardField.ANNOTATOR, StandardField.COMMENTATOR, StandardField.INTRODUCTION,
                    StandardField.FOREWORD, StandardField.AFTERWORD, StandardField.SUBTITLE, StandardField.TITLEADDON,
                    StandardField.LANGUAGE, StandardField.ORIGLANGUAGE, StandardField.EDITION, StandardField.VOLUMES, StandardField.SERIES,
                    StandardField.NUMBER, StandardField.NOTE, StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN,
                    StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType INBOOK = new BibEntryTypeBuilder()
            .withType(StandardEntryType.IN_BOOK)
            .withImportantFields(
                    StandardField.BOOKAUTHOR, StandardField.EDITOR, StandardField.SUBTITLE, StandardField.TITLEADDON,
                    StandardField.MAINTITLE, StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.BOOKSUBTITLE,
                    StandardField.BOOKTITLEADDON, StandardField.VOLUME, StandardField.EDITION, StandardField.PUBLISHER,
                    StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.BOOKTITLE, StandardField.DATE)
            .withDetailFields(StandardField.BOOKAUTHOR, StandardField.EDITOR, StandardField.EDITORA, StandardField.EDITORB,
                    StandardField.EDITORC, StandardField.TRANSLATOR, StandardField.ANNOTATOR, StandardField.COMMENTATOR,
                    StandardField.INTRODUCTION, StandardField.FOREWORD, StandardField.AFTERWORD, StandardField.SUBTITLE,
                    StandardField.TITLEADDON, StandardField.MAINTITLE, StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON,
                    StandardField.BOOKSUBTITLE, StandardField.BOOKTITLEADDON, StandardField.LANGUAGE, StandardField.ORIGLANGUAGE,
                    StandardField.VOLUME, StandardField.PART, StandardField.EDITION, StandardField.VOLUMES, StandardField.SERIES,
                    StandardField.NUMBER, StandardField.NOTE, StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType BOOKINBOOK = new BibEntryTypeBuilder()
            .withType(StandardEntryType.BOOK_IN_BOOK)
            .withImportantFields(INBOOK.getPrimaryOptionalFields())
            .withDetailFields(INBOOK.getSecondaryOptionalFields())
            .withRequiredFields(INBOOK.getRequiredFields())
            .build();

    private static final BibEntryType SUPPBOOK = new BibEntryTypeBuilder()
            .withType(StandardEntryType.SUPP_BOOK)
            .withImportantFields(INBOOK.getPrimaryOptionalFields())
            .withDetailFields(INBOOK.getSecondaryOptionalFields())
            .withRequiredFields(INBOOK.getRequiredFields())
            .build();

    private static final BibEntryType BOOKLET = new BibEntryTypeBuilder()
            .withType(StandardEntryType.BOOKLET)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.HOWPUBLISHED,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS,
                    StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(new OrFields(StandardField.AUTHOR, StandardField.EDITOR), StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.HOWPUBLISHED,
                    StandardField.TYPE, StandardField.NOTE, StandardField.LOCATION, StandardField.CHAPTER, StandardField.PAGES,
                    StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType COLLECTION = new BibEntryTypeBuilder()
            .withType(StandardEntryType.COLLECTION)
            .withImportantFields(
                    StandardField.TRANSLATOR, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE,
                    StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.VOLUME, StandardField.EDITION,
                    StandardField.PUBLISHER, StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.EDITOR, StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC, StandardField.TRANSLATOR,
                    StandardField.ANNOTATOR, StandardField.COMMENTATOR, StandardField.INTRODUCTION, StandardField.FOREWORD,
                    StandardField.AFTERWORD, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE,
                    StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.LANGUAGE, StandardField.ORIGLANGUAGE,
                    StandardField.VOLUME, StandardField.PART, StandardField.EDITION, StandardField.VOLUMES, StandardField.SERIES,
                    StandardField.NUMBER, StandardField.NOTE, StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE,
                    StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL,
                    StandardField.URLDATE)
            .build();

    private static final BibEntryType MVCOLLECTION = new BibEntryTypeBuilder()
            .withType(StandardEntryType.MV_COLLECTION)
            .withImportantFields(StandardField.TRANSLATOR, StandardField.SUBTITLE, StandardField.TITLEADDON,
                    StandardField.EDITION, StandardField.PUBLISHER, StandardField.ISBN, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.EDITOR, StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC, StandardField.TRANSLATOR,
                    StandardField.ANNOTATOR, StandardField.COMMENTATOR, StandardField.INTRODUCTION, StandardField.FOREWORD,
                    StandardField.AFTERWORD, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE,
                    StandardField.ORIGLANGUAGE, StandardField.EDITION, StandardField.VOLUMES, StandardField.SERIES, StandardField.NUMBER,
                    StandardField.NOTE, StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN, StandardField.PAGETOTAL,
                    StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS,
                    StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType INCOLLECTION = new BibEntryTypeBuilder()
            .withType(StandardEntryType.IN_COLLECTION)
            .withImportantFields(StandardField.TRANSLATOR, StandardField.SUBTITLE, StandardField.TITLEADDON,
                    StandardField.MAINTITLE, StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.BOOKSUBTITLE,
                    StandardField.BOOKTITLEADDON, StandardField.VOLUME, StandardField.EDITION, StandardField.PUBLISHER,
                    StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.BOOKTITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITOR, StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC,
                    StandardField.TRANSLATOR, StandardField.ANNOTATOR, StandardField.COMMENTATOR, StandardField.INTRODUCTION,
                    StandardField.FOREWORD, StandardField.AFTERWORD, StandardField.SUBTITLE, StandardField.TITLEADDON,
                    StandardField.MAINTITLE, StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.BOOKSUBTITLE,
                    StandardField.BOOKTITLEADDON, StandardField.LANGUAGE, StandardField.ORIGLANGUAGE, StandardField.VOLUME,
                    StandardField.PART, StandardField.EDITION, StandardField.VOLUMES, StandardField.SERIES, StandardField.NUMBER,
                    StandardField.NOTE, StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN, StandardField.CHAPTER,
                    StandardField.PAGES, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType SUPPCOLLECTION = new BibEntryTypeBuilder()
            .withType(StandardEntryType.SUPP_COLLECTION)
            .withImportantFields(INCOLLECTION.getPrimaryOptionalFields())
            .withDetailFields(INCOLLECTION.getSecondaryOptionalFields())
            .withRequiredFields(INCOLLECTION.getRequiredFields())
            .build();

    private static final BibEntryType MANUAL = new BibEntryTypeBuilder()
            .withType(StandardEntryType.MANUAL)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.EDITION, StandardField.PUBLISHER,
                    StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(new OrFields(StandardField.AUTHOR, StandardField.EDITOR), StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.EDITION,
                    StandardField.TYPE, StandardField.SERIES, StandardField.NUMBER, StandardField.VERSION, StandardField.NOTE,
                    StandardField.ORGANIZATION, StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN, StandardField.CHAPTER,
                    StandardField.PAGES, StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType MISC = new BibEntryTypeBuilder()
            .withType(StandardEntryType.MISC)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.HOWPUBLISHED, StandardField.LOCATION, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(new OrFields(StandardField.AUTHOR, StandardField.EDITOR), StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.HOWPUBLISHED,
                    StandardField.TYPE, StandardField.VERSION, StandardField.NOTE, StandardField.ORGANIZATION, StandardField.LOCATION,
                    StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType ONLINE = new BibEntryTypeBuilder()
                                                                        .withType(StandardEntryType.ON_LINE)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.NOTE, StandardField.ORGANIZATION, StandardField.URLDATE)
            .withRequiredFields(new OrFields(StandardField.AUTHOR, StandardField.EDITOR), StandardField.TITLE, StandardField.DATE, StandardField.URL)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.VERSION,
                    StandardField.NOTE, StandardField.ORGANIZATION, StandardField.ADDENDUM, StandardField.PUBSTATE,
                    StandardField.URLDATE)
            .build();

    private static final BibEntryType PATENT = new BibEntryTypeBuilder()
                                                                        .withType(IEEETranEntryType.PATENT)
            .withImportantFields(StandardField.HOLDER,
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS,
                    StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.NUMBER, StandardField.DATE)
            .withDetailFields(StandardField.HOLDER, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.TYPE,
                    StandardField.VERSION, StandardField.LOCATION, StandardField.NOTE, StandardField.ADDENDUM,
                    StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE,
                    StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType PERIODICAL = new BibEntryTypeBuilder()
                                                                            .withType(IEEETranEntryType.PERIODICAL)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.ISSUETITLE, StandardField.ISSUESUBTITLE, StandardField.ISSN, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.EDITOR, StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITORA, StandardField.EDITORB, StandardField.EDITORC, StandardField.SUBTITLE,
                    StandardField.ISSUETITLE, StandardField.ISSUESUBTITLE, StandardField.LANGUAGE, StandardField.SERIES,
                    StandardField.VOLUME, StandardField.NUMBER, StandardField.ISSUE, StandardField.NOTE,
                    StandardField.ISSN, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType SUPPPERIODICAL = new BibEntryTypeBuilder()
                                                                                .withType(StandardEntryType.SUPP_PERIODICAL)
            .withImportantFields(ARTICLE.getPrimaryOptionalFields())
            .withDetailFields(ARTICLE.getSecondaryOptionalFields())
            .withRequiredFields(ARTICLE.getRequiredFields())
            .build();

    private static final BibEntryType PROCEEDINGS = new BibEntryTypeBuilder()
                                                                             .withType(StandardEntryType.PROCEEDINGS)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE, StandardField.MAINSUBTITLE,
                    StandardField.MAINTITLEADDON, StandardField.EVENTTITLE, StandardField.VOLUME, StandardField.PUBLISHER, StandardField.ISBN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITOR, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE,
                    StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.EVENTTITLE, StandardField.EVENTTITLEADDON,
                    StandardField.EVENTDATE, StandardField.VENUE, StandardField.LANGUAGE, StandardField.VOLUME, StandardField.PART,
                    StandardField.VOLUMES, StandardField.SERIES, StandardField.NUMBER, StandardField.NOTE, StandardField.ORGANIZATION,
                    StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE,
                    StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL,
                    StandardField.URLDATE)
            .build();

    private static final BibEntryType MVPROCEEDINGS = new BibEntryTypeBuilder()
                                                                               .withType(StandardEntryType.MV_PROCEEDINGS)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE, StandardField.MAINSUBTITLE,
                    StandardField.MAINTITLEADDON, StandardField.EVENTTITLE, StandardField.VOLUME, StandardField.PUBLISHER, StandardField.ISBN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITOR, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.EVENTTITLE,
                    StandardField.EVENTTITLEADDON, StandardField.EVENTDATE, StandardField.VENUE, StandardField.LANGUAGE,
                    StandardField.VOLUMES, StandardField.SERIES, StandardField.NUMBER, StandardField.NOTE, StandardField.ORGANIZATION,
                    StandardField.PUBLISHER, StandardField.LOCATION, StandardField.ISBN, StandardField.PAGETOTAL,
                    StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS,
                    StandardField.EPRINTTYPE, StandardField.URL,
                    StandardField.URLDATE)
            .build();

    private static final BibEntryType INPROCEEDINGS = new BibEntryTypeBuilder()
                                                                               .withType(StandardEntryType.IN_PROCEEDINGS)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE,
                    StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.BOOKSUBTITLE,
                    StandardField.BOOKTITLEADDON, StandardField.EVENTTITLE, StandardField.VOLUME, StandardField.PUBLISHER,
                    StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.BOOKTITLE, StandardField.DATE)
            .withDetailFields(StandardField.EDITOR, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.MAINTITLE,
                    StandardField.MAINSUBTITLE, StandardField.MAINTITLEADDON, StandardField.BOOKSUBTITLE, StandardField.BOOKTITLEADDON,
                    StandardField.EVENTTITLE, StandardField.EVENTTITLEADDON, StandardField.EVENTDATE, StandardField.VENUE,
                    StandardField.LANGUAGE, StandardField.VOLUME, StandardField.PART, StandardField.VOLUMES, StandardField.SERIES,
                    StandardField.NUMBER, StandardField.NOTE, StandardField.ORGANIZATION, StandardField.PUBLISHER, StandardField.LOCATION,
                    StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES, StandardField.ADDENDUM,
                    StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE,
                    StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType REFERENCE = new BibEntryTypeBuilder()
                                                                           .withType(StandardEntryType.REFERENCE)
            .withImportantFields(COLLECTION.getPrimaryOptionalFields())
            .withDetailFields(COLLECTION.getSecondaryOptionalFields())
            .withRequiredFields(COLLECTION.getRequiredFields())
            .build();

    private static final BibEntryType MVREFERENCE = new BibEntryTypeBuilder()
                                                                             .withType(StandardEntryType.MV_REFERENCE)
            .withImportantFields(MVCOLLECTION.getPrimaryOptionalFields())
            .withDetailFields(MVCOLLECTION.getSecondaryOptionalFields())
            .withRequiredFields(MVCOLLECTION.getRequiredFields())
            .build();

    private static final BibEntryType INREFERENCE = new BibEntryTypeBuilder()
                                                                             .withType(StandardEntryType.IN_REFERENCE)
            .withImportantFields(INCOLLECTION.getPrimaryOptionalFields())
            .withDetailFields(INCOLLECTION.getSecondaryOptionalFields())
            .withRequiredFields(INCOLLECTION.getRequiredFields())
            .build();

    private static final BibEntryType REPORT = new BibEntryTypeBuilder()
                                                                        .withType(StandardEntryType.REPORT)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.NUMBER, StandardField.ISRN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.TYPE, StandardField.INSTITUTION, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.NUMBER,
                    StandardField.VERSION, StandardField.NOTE, StandardField.LOCATION, StandardField.ISRN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE,
                    StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL,
                    StandardField.URLDATE)
            .build();

    private static final BibEntryType SET = new BibEntryTypeBuilder()
                                                                     .withType(StandardEntryType.SET)
            .withRequiredFields(StandardField.ENTRYSET, StandardField.CROSSREF)
            .build();

    private static final BibEntryType THESIS = new BibEntryTypeBuilder()
                                                                        .withType(StandardEntryType.THESIS)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.CHAPTER,
                    StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS,
                    StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.TYPE, StandardField.INSTITUTION, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.NOTE,
                    StandardField.LOCATION, StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES,
                    StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType UNPUBLISHED = new BibEntryTypeBuilder()
                                                                             .withType(StandardEntryType.UNPUBLISHED)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.HOWPUBLISHED,
                    StandardField.PUBSTATE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.HOWPUBLISHED,
                    StandardField.NOTE, StandardField.LOCATION, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.EVENTTITLE,
                    StandardField.EVENTDATE, StandardField.VENUE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType CONFERENCE = new BibEntryTypeBuilder()
                                                                            .withType(StandardEntryType.CONFERENCE)
            .withImportantFields(INPROCEEDINGS.getPrimaryOptionalFields())
            .withDetailFields(INPROCEEDINGS.getSecondaryOptionalFields())
            .withRequiredFields(INPROCEEDINGS.getRequiredFields())
            .build();

    private static final BibEntryType ELECTRONIC = new BibEntryTypeBuilder()
                                                                            .withType(IEEETranEntryType.ELECTRONIC)
            .withImportantFields(ONLINE.getPrimaryOptionalFields())
            .withDetailFields(ONLINE.getSecondaryOptionalFields())
            .withRequiredFields(ONLINE.getRequiredFields())
            .build();

    private static final BibEntryType MASTERSTHESIS = new BibEntryTypeBuilder()
                                                                               .withType(StandardEntryType.MASTER_THESIS)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.TYPE,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.INSTITUTION, StandardField.DATE)
            .withDetailFields(StandardField.TYPE, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.NOTE,
                    StandardField.LOCATION, StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES,
                    StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType PHDTHESIS = new BibEntryTypeBuilder()
                                                                           .withType(StandardEntryType.PHD_THESIS)
            .withImportantFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.TYPE,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.INSTITUTION, StandardField.DATE)
            .withDetailFields(StandardField.TYPE, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.NOTE,
                    StandardField.LOCATION, StandardField.ISBN, StandardField.CHAPTER, StandardField.PAGES,
                    StandardField.PAGETOTAL, StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType TECHREPORT = new BibEntryTypeBuilder()
                                                                            .withType(StandardEntryType.TECH_REPORT)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.TYPE, StandardField.NUMBER, StandardField.ISRN,
                    StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(StandardField.AUTHOR, StandardField.TITLE, StandardField.INSTITUTION, StandardField.DATE)
            .withDetailFields(StandardField.TYPE, StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE,
                    StandardField.NUMBER, StandardField.VERSION, StandardField.NOTE, StandardField.LOCATION,
                    StandardField.ISRN, StandardField.CHAPTER, StandardField.PAGES, StandardField.PAGETOTAL, StandardField.ADDENDUM,
                    StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE,
                    StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType WWW = new BibEntryTypeBuilder()
            .withType(StandardEntryType.WWW)
            .withImportantFields(ONLINE.getPrimaryOptionalFields())
            .withDetailFields(ONLINE.getSecondaryOptionalFields())
            .withRequiredFields(ONLINE.getRequiredFields())
            .build();

    private static final BibEntryType SOFTWARE = new BibEntryTypeBuilder()
                                                                          .withType(StandardEntryType.SOFTWARE)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.HOWPUBLISHED, StandardField.LOCATION, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(new OrFields(StandardField.AUTHOR, StandardField.EDITOR), StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.HOWPUBLISHED,
                    StandardField.TYPE, StandardField.VERSION, StandardField.NOTE, StandardField.ORGANIZATION, StandardField.LOCATION,
                    StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    private static final BibEntryType DATASET = new BibEntryTypeBuilder()
                                                                         .withType(StandardEntryType.DATASET)
            .withImportantFields(
                    StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.HOWPUBLISHED, StandardField.LOCATION, StandardField.DOI,
                    StandardField.EPRINT, StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .withRequiredFields(new OrFields(StandardField.AUTHOR, StandardField.EDITOR), StandardField.TITLE, StandardField.DATE)
            .withDetailFields(StandardField.SUBTITLE, StandardField.TITLEADDON, StandardField.LANGUAGE, StandardField.EDITION, StandardField.HOWPUBLISHED,
                    StandardField.TYPE, StandardField.VERSION, StandardField.NOTE, StandardField.ORGANIZATION, StandardField.LOCATION,
                    StandardField.ADDENDUM, StandardField.PUBSTATE, StandardField.DOI, StandardField.EPRINT,
                    StandardField.EPRINTCLASS, StandardField.EPRINTTYPE, StandardField.URL, StandardField.URLDATE)
            .build();

    public static final List<BibEntryType> ALL = Arrays.asList(ARTICLE, BOOK, MVBOOK, INBOOK, BOOKINBOOK, SUPPBOOK,
            BOOKLET, COLLECTION, MVCOLLECTION, INCOLLECTION, SUPPCOLLECTION, MANUAL, MISC, ONLINE, PATENT, PERIODICAL,
            SUPPPERIODICAL, PROCEEDINGS, MVPROCEEDINGS, INPROCEEDINGS, REFERENCE, MVREFERENCE, INREFERENCE, REPORT, SET,
            THESIS, UNPUBLISHED, CONFERENCE, ELECTRONIC, MASTERSTHESIS, PHDTHESIS, TECHREPORT, WWW, SOFTWARE, DATASET);
}
