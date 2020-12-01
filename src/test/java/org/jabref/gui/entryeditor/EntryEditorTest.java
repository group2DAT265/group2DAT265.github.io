package org.jabref.gui.entryeditor;

import org.jabref.gui.BasePanel;
import org.jabref.gui.externalfiletype.ExternalFileTypes;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;

public class EntryEditorTest {

    BasePanel panel;
    ExternalFileTypes externalFileTypes;
    EntryEditor editor;

    @Test
    void testConstructor() {
        editor = spy(new EntryEditor(panel, externalFileTypes));
    }
}
