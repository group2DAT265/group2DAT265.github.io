package org.jabref.cli;

import org.apache.commons.cli.ParseException;
import org.jabref.logic.importer.ParserResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

public class ArgumentProcessorTest {
    String[] args;
    ArgumentProcessor.Mode startupMode;
    ArgumentProcessor ap;

    @Test
    void constructorTest() throws ParseException {
        ap = spy(new ArgumentProcessor(args, startupMode));
    }

    @Test
    void getParserResultsTest() {
        List<ParserResult> pr = new ArrayList<>();
        assertEquals(pr, ap.getParserResults());
    }

    @Test
    void isBlankTest() {
        Mockito.when(ap.isBlank()).thenReturn(true);
    }

    @Test
    void shouldShutDownTest() {
        Mockito.when(ap.shouldShutDown()).thenReturn(true);
    }
}
