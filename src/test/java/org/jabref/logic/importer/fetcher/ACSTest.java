package org.jabref.logic.importer.fetcher;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.support.DisabledOnCIServer;
import org.jabref.testutils.category.FetcherTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FetcherTest
class ACSTest {
    private ACS finder;
    private BibEntry entry;

    @BeforeEach
    void setUp() {
        finder = new ACS();
        entry = new BibEntry();
    }

    @Test
    void findByDOI() throws IOException {
        entry.setField(StandardField.DOI, "10.1021/bk-2006-STYG.ch014");

        assertEquals(
                Optional.of(new URL("https://pubs.acs.org/doi/pdf/10.1021/bk-2006-STYG.ch014")),
                finder.findFullText(entry)
        );
    }

    @Test
    void notFoundByDOI() throws IOException {
        entry.setField(StandardField.DOI, "10.1021/bk-2006-WWW.ch014");

        assertEquals(Optional.empty(), finder.findFullText(entry));
    }

    @Test
    void entityWithoutDoi() throws IOException {
        assertEquals(Optional.empty(), finder.findFullText(entry));
    }

    @Test
    void trustLevel() {
        assertEquals(TrustLevel.PUBLISHER, finder.getTrustLevel());
    }
}
