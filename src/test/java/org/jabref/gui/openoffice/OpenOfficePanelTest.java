package org.jabref.gui.openoffice;

import org.jabref.gui.JabRefFrame;
import org.jabref.gui.keyboard.KeyBindingRepository;
import org.jabref.logic.openoffice.OpenOfficePreferences;
import org.jabref.preferences.JabRefPreferences;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;

public class OpenOfficePanelTest {

    JabRefFrame frame;
    JabRefPreferences jabRefPreferences;
    OpenOfficePreferences ooPrefs;
    KeyBindingRepository keyBindingRepository;
    OpenOfficePanel oop;

    @Test
    void testConstructor() {
        oop = spy(new OpenOfficePanel(frame, jabRefPreferences, ooPrefs, keyBindingRepository));
    }

}
