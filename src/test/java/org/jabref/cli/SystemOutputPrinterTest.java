package org.jabref.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

class SystemOutputPrinterTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setStatusTest() {
        SystemOutputPrinter sop = mock(SystemOutputPrinter.class);
        verify(sop, times(1)).setStatus("message");
        assertEquals("message", outputStreamCaptor.toString().trim());
    }

    @Test
    void showMessageTest() {
        SystemOutputPrinter sop = mock(SystemOutputPrinter.class);
        verify(sop, times(1)).showMessage("message");
        assertEquals("message", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
