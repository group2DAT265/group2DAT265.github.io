package org.jabref.cli;

import java.nio.file.Path;

import org.jabref.gui.auximport.AuxParserResultViewModel;
import org.jabref.logic.auxparser.AuxParser;
import org.jabref.logic.auxparser.AuxParserResult;
import org.jabref.logic.auxparser.DefaultAuxParser;
import org.jabref.model.database.BibDatabase;
import org.jabref.model.strings.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuxCommandLine {
    private final String auxFile;
    private final BibDatabase database;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuxCommandLine.class);

    public AuxCommandLine(String auxFile, BibDatabase database) {
        this.auxFile = StringUtil.getCorrectFileName(auxFile, "aux");
        this.database = database;
    }

    public BibDatabase perform() {
        BibDatabase subDatabase = null;

        if (!auxFile.isEmpty() && (database != null)) {
            AuxParser auxParser = new DefaultAuxParser(database);
            AuxParserResult result = auxParser.parse(Path.of(auxFile));
            subDatabase = result.getGeneratedBibDatabase();
            // print statistics
            LOGGER.info(new AuxParserResultViewModel(result).getInformation(true));
        }
        return subDatabase;
    }
}
