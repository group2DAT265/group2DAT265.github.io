package org.jabref.gui.bibtexextractor;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.StandardEntryType;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class BibtexExtractorTest {

    private BibtexExtractor bibtexExtractor;

    @Test
    public void generateBibEntryWithEverything() {
        bibtexExtractor = new BibtexExtractor();
        BibEntry actual = bibtexExtractor.extract("2020 p.100 LASTNAME Hafstrom INITIALS S.H http://foo.bar");
        BibEntry expected = new BibEntry(StandardEntryType.ARTICLE);
        expected.setField(StandardField.URL, "http://foo.bar");
        expected.setField(StandardField.AUTHOR, "Sebastian Hafstrom");
        expected.setField(StandardField.YEAR, "2020");
        expected.setField(StandardField.PAGES, "p.100");
        assertNotEquals(expected, actual);
    }
}
