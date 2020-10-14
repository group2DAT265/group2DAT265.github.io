package org.jabref.cli;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SystemOutputPrinterTest {

    @Test
    void setStatusTest() {
        SystemOutputPrinter sop = mock(SystemOutputPrinter.class);
        sop.setStatus("");
        verify(sop, times(1)).setStatus("");
    }

    @Test
    void showMessageTest() {
        SystemOutputPrinter sop = mock(SystemOutputPrinter.class);
        sop.showMessage("", "", 1);
        verify(sop, times(1)).showMessage("", "", 1);
    }
}
