package org.jabref.cli;

import java.util.Collections;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JabRefCLITest {

    @Test
    void parsingLongOptions() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"--nogui", "--help", "--blank", "--version", "--import=some/file", "--output=some/export/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals("some/file", cli.getFileImport());
        assertTrue(cli.isDisableGui());
        assertTrue(cli.isHelp());
        assertTrue(cli.isBlank());
        assertTrue(cli.isShowVersion());
        assertEquals("some/file", cli.getImportToOpenBase());
        assertEquals("some/export/file", cli.getFileExport());
    }

    @Test
    void parsingShortOptions() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-h", "-b", "-v", "-i=some/file", "-o=some/export/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals("some/file", cli.getFileImport());
        assertTrue(cli.isFileImport());
        assertTrue(cli.isDisableGui());
        assertTrue(cli.isHelp());
        assertTrue(cli.isBlank());
        assertTrue(cli.isShowVersion());
        assertTrue(cli.isImportToOpenBase());
        assertEquals("some/export/file", cli.getFileExport());
        assertTrue(cli.isFileExport());
    }

    @Test
    void preferencesExport() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-h", "-b", "-v", "-x=some/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals("some/file", cli.getPreferencesExport());
        assertTrue(cli.isPreferencesExport());
        assertTrue(cli.isDisableGui());
        assertTrue(cli.isHelp());
        assertTrue(cli.isBlank());
        assertTrue(cli.isShowVersion());
    }

    @Test
    void preferencesImport() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-p=some/file"});
        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals("some/file", cli.getPreferencesImport());
        assertTrue(cli.isPreferencesImport());
    }

    @Test
    void auxImport() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-a=some/file"});
        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals("some/file", cli.getAuxImport());
        assertTrue(cli.isAuxImport());
    }

    @Test
    void preferencesReset() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-d=all"});
        assertEquals("all", cli.getPreferencesReset());
        assertTrue(cli.isPreferencesReset());
    }

    @Test
    void recognizesImportBibtex() throws ParseException {
        String bibtex = "@article{test, title=\"test title\"}";
        JabRefCLI cli = new JabRefCLI(new String[]{"-ib", bibtex});
        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertTrue(cli.isBibtexImport());
        assertEquals(bibtex, cli.getBibtexImport());
    }

    @Test
    void recognizesImportBibtexLong() throws ParseException {
        String bibtex = "@article{test, title=\"test title\"}";
        JabRefCLI cli = new JabRefCLI(new String[]{"-importBibtex", bibtex});
        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertTrue(cli.isBibtexImport());
        assertEquals(bibtex, cli.getBibtexImport());
    }

    @Test
    void getExportMatchesSyntaxTest() {
        assertEquals("field", JabRefCLI.getExportMatchesSyntax());
        assertEquals("file", JabRefCLI.getExportMatchesSyntax());
        assertEquals("exportFormat", JabRefCLI.getExportMatchesSyntax());
    }

    @Test
    void loadSessionTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{});
        assertTrue(cli.isLoadSession());
    }

    @Test
    void exportMatchesTest() throws ParseException {
        String author = "test";
        JabRefCLI cli = new JabRefCLI(new String[]{"-m=some/file", author});
        assertTrue(cli.isExportMatches());
        assertEquals(author, cli.getExportMatches());
    }

    @Test
    void fetcherTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-f=some/file:query"});
        assertTrue(cli.isFetcherEngine());
        assertEquals("some/file:query", cli.getFetcherEngine());
    }

    @Test
    void debugLoggingTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{});
        assertTrue(cli.isDebugLogging());
    }

    @Test
    void generateCitationKeysTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{});
        assertTrue(cli.isGenerateCitationKeys());
    }

    @Test
    void automaticallySetFileLinksTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{});
        assertTrue(cli.isGenerateCitationKeys());
    }
}
