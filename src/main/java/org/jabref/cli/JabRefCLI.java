package org.jabref.cli;

import java.util.List;

import org.jabref.gui.Globals;
import org.jabref.logic.l10n.Localization;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JabRefCLI {

    private static final int WIDTH = 100; // Number of characters per line
    private static final Logger LOGGER = LoggerFactory.getLogger(JabRefCLI.class);

    // Constants to avoid duplication and critical code smells
    private static final String PREXP = "prexp";
    private static final String PRIMP = "primp";
    private static final String PRDEF = "prdef";
    private static final String OUTPUT = "output";
    private static final String IMPORTBIBTEX = "importBibtex";
    private static final String IMPORT = "import";
    private static final String IMPORTTOOPEN = "importToOpen";
    private static final String FETCH = "fetch";
    private static final String EXPORTMATCHES = "exportMatches";
    private static final String SS = "%s: '%s'";
    private static final String FILEFORMAT = "FILE[,FORMAT]";

    private final CommandLine cl;
    private final List<String> leftOver;

    public JabRefCLI(String[] args) throws ParseException {
        Options options = getOptions();

        this.cl = new DefaultParser().parse(options, args, true);
        this.leftOver = cl.getArgList();
    }

    public static String getExportMatchesSyntax() {
        return String.format("[%s]searchTerm,outputFile:%s[,%s]",
                Localization.lang("field"),
                Localization.lang("file"),
                Localization.lang("exportFormat"));
    }

    public boolean isHelp() {
        return cl.hasOption("help");
    }

    public boolean isShowVersion() {
        return cl.hasOption("version");
    }

    public boolean isBlank() {
        return cl.hasOption("blank");
    }

    public boolean isLoadSession() {
        return cl.hasOption("loads");
    }

    public boolean isDisableGui() {
        return cl.hasOption("nogui");
    }

    public boolean isPreferencesExport() {
        return cl.hasOption(PREXP);
    }

    public String getPreferencesExport() {
        return cl.getOptionValue(PREXP, "jabref_prefs.xml");
    }

    public boolean isPreferencesImport() {
        return cl.hasOption(PRIMP);
    }

    public String getPreferencesImport() {
        return cl.getOptionValue(PRIMP, "jabref_prefs.xml");
    }

    public boolean isPreferencesReset() {
        return cl.hasOption(PRDEF);
    }

    public String getPreferencesReset() {
        return cl.getOptionValue(PRDEF);
    }

    public boolean isFileExport() {
        return cl.hasOption(OUTPUT);
    }

    public String getFileExport() {
        return cl.getOptionValue(OUTPUT);
    }

    public boolean isBibtexImport() {
        return cl.hasOption(IMPORTBIBTEX);
    }

    public String getBibtexImport() {
        return cl.getOptionValue(IMPORTBIBTEX);
    }

    public boolean isFileImport() {
        return cl.hasOption(IMPORT);
    }

    public String getFileImport() {
        return cl.getOptionValue(IMPORT);
    }

    public boolean isAuxImport() {
        return cl.hasOption("aux");
    }

    public String getAuxImport() {
        return cl.getOptionValue("aux");
    }

    public boolean isImportToOpenBase() {
        return cl.hasOption(IMPORTTOOPEN);
    }

    public String getImportToOpenBase() {
        return cl.getOptionValue(IMPORTTOOPEN);
    }

    public boolean isDebugLogging() {
        return cl.hasOption("debug");
    }

    public boolean isFetcherEngine() {
        return cl.hasOption(FETCH);
    }

    public String getFetcherEngine() {
        return cl.getOptionValue(FETCH);
    }

    public boolean isExportMatches() {
        return cl.hasOption(EXPORTMATCHES);
    }

    public String getExportMatches() {
        return cl.getOptionValue(EXPORTMATCHES);
    }

    public boolean isGenerateCitationKeys() {
        return cl.hasOption("generateCitationKeys");
    }

    public boolean isAutomaticallySetFileLinks() {
        return cl.hasOption("automaticallySetFileLinks");
    }

    private static Options getOptions() {
        Options options = new Options();

        // boolean options
        options.addOption("h", "help", false, Localization.lang("Display help on command line options"));
        options.addOption("n", "nogui", false, Localization.lang("No GUI. Only process command line options"));
        options.addOption("asfl", "automaticallySetFileLinks", false, Localization.lang("Automatically set file links"));
        options.addOption("g", "generateCitationKeys", false, Localization.lang("Regenerate all keys for the entries in a BibTeX file"));
        options.addOption("b", "blank", false, Localization.lang("Do not open any files at startup"));
        options.addOption("v", "version", false, Localization.lang("Display version"));
        options.addOption(null, "debug", false, Localization.lang("Show debug level messages"));

        // The "-console" option is handled by the install4j launcher
        options.addOption(null, "console", false, Localization.lang("Show console output (only when the launcher is used)"));

        options.addOption(Option
                .builder("i")
                                .longOpt(IMPORT)
                                .desc(String.format(SS, Localization.lang("Import file"), "-i library.bib"))
                .hasArg()
                                .argName(FILEFORMAT)
                .build());

        options.addOption(Option
                .builder()
                                .longOpt(IMPORTTOOPEN)
                .desc(Localization.lang("Same as --import, but will be imported to the opened tab"))
                .hasArg()
                                .argName(FILEFORMAT)
                .build());

        options.addOption(Option
                .builder("ib")
                                .longOpt(IMPORTBIBTEX)
                                .desc(String.format(SS, Localization.lang("Import BibTeX"), "-ib @article{entry}"))
                .hasArg()
                .argName("BIBTEXT_STRING")
                .build());

        options.addOption(Option
                .builder("o")
                                .longOpt(OUTPUT)
                                .desc(String.format(SS, Localization.lang("Export an input to a file"), "-i db.bib -o db.htm,html"))
                .hasArg()
                                .argName(FILEFORMAT)
                .build());

        options.addOption(Option
                .builder("m")
                                .longOpt(EXPORTMATCHES)
                                .desc(String.format(SS, Localization.lang("Matching"), "-i db.bib -m author=Newton,search.htm,html"))
                .hasArg()
                .argName("QUERY,FILE[,FORMAT]")
                .build());

        options.addOption(Option
                .builder("f")
                                .longOpt(FETCH)
                                .desc(String.format(SS, Localization.lang("Run fetcher"), "-f Medline/PubMed:cancer"))
                .hasArg()
                .argName("FETCHER:QUERY")
                .build());

        options.addOption(Option
                .builder("a")
                .longOpt("aux")
                                .desc(String.format(SS, Localization.lang("Sublibrary from AUX to BibTeX"), "-a thesis.aux,new.bib"))
                .hasArg()
                .argName("FILE[.aux],FILE[.bib] FILE")
                .build());

        options.addOption(Option
                .builder("x")
                                .longOpt(PREXP)
                                .desc(String.format(SS, Localization.lang("Export preferences to a file"), "-x prefs.xml"))
                .hasArg()
                .argName("[FILE]")
                .build());

        options.addOption(Option
                .builder("p")
                                .longOpt(PRIMP)
                                .desc(String.format(SS, Localization.lang("Import preferences from a file"), "-p prefs.xml"))
                .hasArg()
                .argName("[FILE]")
                .build());

        options.addOption(Option
                .builder("d")
                                .longOpt(PRDEF)
                                .desc(String.format(SS, Localization.lang("Reset preferences"), "-d mainFontSize,newline' or '-d all"))
                .hasArg()
                .argName("KEY1[,KEY2][,KEYn] | all")
                .build());

        return options;
    }

    public void displayVersion() {
        System.out.println(getVersionInfo());
    }

    public static void printUsage() {
        String header = "";

        String importFormats = Globals.IMPORT_FORMAT_READER.getImportFormatList();
        String importFormatsList = String.format("%s:%n%s%n", Localization.lang("Available import formats"), importFormats);

        String outFormats = Globals.exportFactory.getExportersAsString(70, 20, "");
        String outFormatsList = String.format("%s: %s%n", Localization.lang("Available export formats"), outFormats);

        String footer = '\n' + importFormatsList + outFormatsList + "\nPlease report issues at https://github.com/JabRef/jabref/issues.";

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(WIDTH, "jabref [OPTIONS] [BIBTEX_FILE]\n\nOptions:", header, getOptions(), footer, true);
    }

    private String getVersionInfo() {
        return String.format("JabRef %s", Globals.BUILD_INFO.version);
    }

    public List<String> getLeftOver() {
        return leftOver;
    }
}
