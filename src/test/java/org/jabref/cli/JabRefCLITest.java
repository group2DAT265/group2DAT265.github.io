package org.jabref.cli;

import java.util.Collections;

import org.apache.commons.cli.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class JabRefCLITest {

    private static String someFile = "some/file";

    @Test
    void parsingLongOptions() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"--nogui", "--help", "--blank", "--version", "--import=some/file", "--output=some/export/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals(someFile, cli.getFileImport());
        assertTrue(cli.isDisableGui());
        assertTrue(cli.isHelp());
        assertTrue(cli.isBlank());
        assertTrue(cli.isShowVersion());
        assertEquals("some/export/file", cli.getFileExport());
    }

    @Test
    void parsingShortOptions() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-h", "-b", "-v", "-i=some/file", "-o=some/export/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals(someFile, cli.getFileImport());
        assertTrue(cli.isFileImport());
        assertTrue(cli.isDisableGui());
        assertTrue(cli.isHelp());
        assertTrue(cli.isBlank());
        assertTrue(cli.isShowVersion());
        assertEquals(someFile, cli.getImportToOpenBase());
        assertEquals("some/export/file", cli.getFileExport());
        assertTrue(cli.isFileExport());
    }

    @Test
    void preferencesExport() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-n", "-h", "-b", "-v", "-x=some/file"});

        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals(someFile, cli.getPreferencesExport());
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
        assertEquals(someFile, cli.getPreferencesImport());
        assertTrue(cli.isPreferencesImport());
    }

    @Test
    void auxImport() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-a=some/file"});
        assertEquals(Collections.emptyList(), cli.getLeftOver());
        assertEquals(someFile, cli.getAuxImport());
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
        assertEquals("[field]searchTerm,outputFile:file[,exportFormat]", JabRefCLI.getExportMatchesSyntax());
    }

    @Test
    void exportMatchesTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-m=some/file"});
        assertTrue(cli.isExportMatches());
        assertEquals(someFile, cli.getExportMatches());
    }

    @Test
    void fetcherTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-f=some/file:query"});
        assertTrue(cli.isFetcherEngine());
        assertEquals("some/file:query", cli.getFetcherEngine());
    }

    @Test
    void debugLoggingTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"--debug"});
        assertTrue(cli.isDebugLogging());
    }

    @Test
    void generateCitationKeysTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-g"});
        assertTrue(cli.isGenerateCitationKeys());
    }

    @Test
    void automaticallySetFileLinksTest() throws ParseException {
        JabRefCLI cli = new JabRefCLI(new String[]{"-asfl"});
        assertTrue(cli.isAutomaticallySetFileLinks());
    }
}
