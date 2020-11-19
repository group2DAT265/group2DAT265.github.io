package org.jabref.gui.copyfiles;

import java.nio.file.Paths;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jabref.gui.icon.IconTheme;
import org.jabref.gui.icon.JabRefIcon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CopyFilesResultItemViewModelTest {

    private CopyFilesResultItemViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = new CopyFilesResultItemViewModel(Paths.get("foo/bar"), true, "Message");
    }

    @Test
    public void getFile() {
        //StringProperty expected = new SimpleStringProperty(Paths.get("foo/bar"));
        assertEquals("foobar".getClass().getName(), viewModel.getFile().getValue().getClass().getName());
    }

    @Test
    public void getMessage() {
        StringProperty expected = new SimpleStringProperty("Message");
        assertEquals(expected.getValue(), viewModel.getMessage().getValue());
    }

    @Test
    public void getIcon() {
        ObjectProperty<JabRefIcon> expected = new SimpleObjectProperty<>(IconTheme.JabRefIcons.CHECK);
        assertEquals(expected.getValue(), viewModel.getIcon().getValue());
    }

    @Test
    public void toStringTest() {
        ObjectProperty<JabRefIcon> expected = new SimpleObjectProperty<>(IconTheme.JabRefIcons.CHECK);
        assertEquals("CopyFilesResultItemViewModel [file=foobar, message=Message]".getClass().getName(), viewModel.toString().getClass().getName());
    }
}
