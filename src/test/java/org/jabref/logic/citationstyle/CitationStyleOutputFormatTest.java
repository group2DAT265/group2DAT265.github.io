package org.jabref.logic.citationstyle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitationStyleOutputFormatTest {

    private static final String INPUTFORMAT = "asciidoc";
    private static final String INPUTSTRING = "asciidoc";

    @Test
    void getFormatTest() {
        String expFormat = CitationStyleOutputFormat.ASCII_DOC.getFormat();
        assertEquals(INPUTFORMAT, expFormat);
    }

    @Test
    void getStringTest() {
        String expString = CitationStyleOutputFormat.ASCII_DOC.toString();
        assertEquals(INPUTSTRING, expString);
    }
}
