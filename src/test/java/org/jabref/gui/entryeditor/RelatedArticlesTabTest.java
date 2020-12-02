package org.jabref.gui.entryeditor;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.jabref.gui.DialogService;
import org.jabref.testutils.category.GUITest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.mock;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@GUITest
@ExtendWith(ApplicationExtension.class)
public class RelatedArticlesTabTest {

    private Stage stage;
    private Scene scene;
    private TabPane pane;
    private RelatedArticlesTab relatedArticlesTab;

    @Start
    public void onStart(Stage stage) {
        EntryEditor entryEditor = mock(EntryEditor.class);
        EntryEditorPreferences entryEditorPreferences = mock(EntryEditorPreferences.class);
        DialogService dialogService = mock(DialogService.class);
        relatedArticlesTab = new RelatedArticlesTab(entryEditor, entryEditorPreferences, dialogService);
        pane = new TabPane(
                relatedArticlesTab
        );
        scene = new Scene(pane);
        this.stage = stage;

        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.show();
        pane.getSelectionModel().select(0);

    }

    @Test
    public void test1(FxRobot robot) {
        robot.clickOn(1200, 500);
        robot.interrupt(100);
    }
}
