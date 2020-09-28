package org.jabref.model.entry;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.EntryType;
import org.jabref.model.entry.types.IEEETranEntryType;
import org.jabref.model.entry.types.StandardEntryType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CrossrefTest {

    private BibEntry parent;
    private BibEntry child;
    private BibDatabase db;

    @BeforeEach
    void setup() {
        parent = new BibEntry(StandardEntryType.PROCEEDINGS);
        parent.setCiteKey("parent");
        parent.setField(StandardField.IDS, "parent_IDS");
        parent.setField(StandardField.XREF, "parent_XREF");
        parent.setField(StandardField.ENTRYSET, "parent_ENTRYSET");
        parent.setField(StandardField.RELATED, "parent_RELATED");
        parent.setField(StandardField.SORTKEY, "parent_SORTKEY");

        parent.setField(StandardField.AUTHOR, "parent_AUTHOR");

        parent.setField(StandardField.TITLE, "parent_TITLE");
        parent.setField(StandardField.SUBTITLE, "parent_SUBTITLE");
        parent.setField(StandardField.TITLEADDON, "parent_TITLEADDON");
        parent.setField(StandardField.SHORTTITLE, "parent_SHORTTITLE");

        child = new BibEntry(StandardEntryType.IN_PROCEEDINGS);
        child.setField(StandardField.CROSSREF, "parent");

        db = new BibDatabase(Arrays.asList(parent, child));
    }

    @ParameterizedTest
    @EnumSource(value = StandardField.class, names = {"IDS", "XREF", "ENTRYSET", "RELATED", "SORTKEY"})
    void forbiddenFields(StandardField field) {
        Optional<String> childField = child.getResolvedFieldOrAlias(field, db);
        assertTrue(childField.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("authorInheritanceSource")
    void authorInheritance(EntryType parentType, EntryType childType) {
        parent.setType(parentType);
        child.setType(childType);

        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.AUTHOR, null),
                child.getResolvedFieldOrAlias(StandardField.AUTHOR, db)
        );

        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.AUTHOR, null),
                child.getResolvedFieldOrAlias(StandardField.BOOKAUTHOR, db)
        );
    }

    private static Stream<Arguments> authorInheritanceSource() {
        return Stream.of(
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.IN_BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.BOOK_IN_BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.SUPP_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.IN_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.BOOK_IN_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.SUPP_BOOK)
        );
    }

    @ParameterizedTest
    @MethodSource("mainTitleInheritanceSource")
    void mainTitleInheritance(EntryType parentType, EntryType childType) {
        parent.setType(parentType);
        child.setType(childType);

        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.TITLE, null),
                child.getResolvedFieldOrAlias(StandardField.MAINTITLE, db)
        );
        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.SUBTITLE, null),
                child.getResolvedFieldOrAlias(StandardField.MAINSUBTITLE, db)
        );
        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.TITLEADDON, null),
                child.getResolvedFieldOrAlias(StandardField.MAINTITLEADDON, db)
        );
    }

    private static Stream<Arguments> mainTitleInheritanceSource() {
        return Stream.of(
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.IN_BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.BOOK_IN_BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.SUPP_BOOK),
                         Arguments.of(StandardEntryType.MV_COLLECTION, StandardEntryType.COLLECTION),
                         Arguments.of(StandardEntryType.MV_COLLECTION, StandardEntryType.IN_COLLECTION),
                         Arguments.of(StandardEntryType.MV_COLLECTION, StandardEntryType.SUPP_COLLECTION),
                         Arguments.of(StandardEntryType.MV_PROCEEDINGS, StandardEntryType.PROCEEDINGS),
                         Arguments.of(StandardEntryType.MV_PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS),
                         Arguments.of(StandardEntryType.MV_REFERENCE, StandardEntryType.REFERENCE),
                         Arguments.of(StandardEntryType.MV_REFERENCE, StandardEntryType.IN_REFERENCE)
        );
    }

    @ParameterizedTest
    @MethodSource("bookTitleInheritanceSource")
    void bookTitleInheritance(EntryType parentType, EntryType childType) {
        parent.setType(parentType);
        child.setType(childType);

        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.TITLE, null),
                child.getResolvedFieldOrAlias(StandardField.BOOKTITLE, db)
        );
        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.SUBTITLE, null),
                child.getResolvedFieldOrAlias(StandardField.BOOKSUBTITLE, db)
        );
        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.TITLEADDON, null),
                child.getResolvedFieldOrAlias(StandardField.BOOKTITLEADDON, db)
        );
    }

    private static Stream<Arguments> bookTitleInheritanceSource() {
        return Stream.of(
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.IN_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.BOOK_IN_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.SUPP_BOOK),
                         Arguments.of(StandardEntryType.COLLECTION, StandardEntryType.IN_COLLECTION),
                         Arguments.of(StandardEntryType.COLLECTION, StandardEntryType.SUPP_COLLECTION),
                         Arguments.of(StandardEntryType.REFERENCE, StandardEntryType.IN_REFERENCE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS)
        );
    }

    @ParameterizedTest
    @MethodSource("journalTitleInheritanceSource")
    void journalTitleInheritance(EntryType parentType, EntryType childType) {
        parent.setType(parentType);
        child.setType(childType);

        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.TITLE, null),
                child.getResolvedFieldOrAlias(StandardField.JOURNALTITLE, db)
        );
        assertEquals(
                parent.getResolvedFieldOrAlias(StandardField.SUBTITLE, null),
                child.getResolvedFieldOrAlias(StandardField.JOURNALSUBTITLE, db)
        );
    }

    private static Stream<Arguments> journalTitleInheritanceSource() {
        return Stream.of(
                         Arguments.of(IEEETranEntryType.PERIODICAL, StandardEntryType.ARTICLE),
                         Arguments.of(IEEETranEntryType.PERIODICAL, StandardEntryType.SUPP_PERIODICAL)
        );
    }

    @ParameterizedTest
    @MethodSource("noTitleInheritanceSource")
    void noTitleInheritance(EntryType parentType, EntryType childType) {
        parent.setType(parentType);
        child.setType(childType);

        assertTrue(child.getResolvedFieldOrAlias(StandardField.TITLE, db).isEmpty());
        assertTrue(child.getResolvedFieldOrAlias(StandardField.SUBTITLE, db).isEmpty());
        assertTrue(child.getResolvedFieldOrAlias(StandardField.TITLEADDON, db).isEmpty());
        assertTrue(child.getResolvedFieldOrAlias(StandardField.SHORTTITLE, db).isEmpty());
    }

    private static Stream<Arguments> noTitleInheritanceSource() {
        return Stream.of(
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.IN_BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.BOOK_IN_BOOK),
                         Arguments.of(StandardEntryType.MV_BOOK, StandardEntryType.SUPP_BOOK),
                         Arguments.of(StandardEntryType.MV_COLLECTION, StandardEntryType.COLLECTION),
                         Arguments.of(StandardEntryType.MV_COLLECTION, StandardEntryType.IN_COLLECTION),
                         Arguments.of(StandardEntryType.MV_COLLECTION, StandardEntryType.SUPP_COLLECTION),
                         Arguments.of(StandardEntryType.MV_PROCEEDINGS, StandardEntryType.PROCEEDINGS),
                         Arguments.of(StandardEntryType.MV_PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS),
                         Arguments.of(StandardEntryType.MV_REFERENCE, StandardEntryType.REFERENCE),
                         Arguments.of(StandardEntryType.MV_REFERENCE, StandardEntryType.IN_REFERENCE),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.IN_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.BOOK_IN_BOOK),
                         Arguments.of(StandardEntryType.BOOK, StandardEntryType.SUPP_BOOK),
                         Arguments.of(StandardEntryType.COLLECTION, StandardEntryType.IN_COLLECTION),
                         Arguments.of(StandardEntryType.COLLECTION, StandardEntryType.SUPP_COLLECTION),
                         Arguments.of(StandardEntryType.REFERENCE, StandardEntryType.IN_REFERENCE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS)
        );
    }

    @ParameterizedTest
    @MethodSource("sameNameInheritance")
    void sameNameInheritance(EntryType parentType, EntryType childType, StandardField field) {
        parent.setType(parentType);
        child.setType(childType);

        assertTrue(parent.setField(field, "parent_FIELD").isPresent());

        assertEquals(
                parent.getResolvedFieldOrAlias(field, null),
                child.getResolvedFieldOrAlias(field, db)
        );
    }

    private static Stream<Arguments> sameNameInheritance() {
        return Stream.of(
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ABSTRACT),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ADDENDUM),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ADDRESS),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.AFTERWORD),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ANNOTE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ANNOTATION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ANNOTATOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ARCHIVEPREFIX),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ASSIGNEE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.AUTHOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.BOOKPAGINATION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.CHAPTER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.COMMENTATOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.COMMENT),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.DATE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.DAY),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.DAYFILED),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.DOI),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORA),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORB),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORC),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORTYPE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORATYPE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORBTYPE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EDITORCTYPE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EID),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EPRINT),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EPRINTCLASS),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EPRINTTYPE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EVENTDATE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EVENTTITLE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.EVENTTITLEADDON),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.FILE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.FOREWORD),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.FOLDER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.GENDER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.HOLDER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.HOWPUBLISHED),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.INSTITUTION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.INTRODUCTION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ISBN),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ISRN),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ISSN),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ISSUE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ISSUETITLE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ISSUESUBTITLE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.JOURNAL),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.KEY),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.KEYWORDS),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.LANGUAGE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.LOCATION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.MONTH),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.MONTHFILED),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.NAMEADDON),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.NATIONALITY),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.NOTE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.NUMBER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ORGANIZATION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ORIGDATE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.ORIGLANGUAGE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PAGES),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PAGETOTAL),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PAGINATION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PART),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PDF),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PMID),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PS),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PUBLISHER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PUBSTATE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.PRIMARYCLASS),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.REPORTNO),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.REVIEW),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.REVISION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.SCHOOL),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.SERIES),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.SHORTAUTHOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.SHORTEDITOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.SORTNAME),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.TRANSLATOR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.TYPE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.URI),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.URL),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.URLDATE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.VENUE),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.VERSION),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.VOLUME),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.VOLUMES),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.YEAR),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.YEARFILED),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.MR_NUMBER),
                         Arguments.of(StandardEntryType.PROCEEDINGS, StandardEntryType.IN_PROCEEDINGS, StandardField.XDATA)
        );
    }
}
