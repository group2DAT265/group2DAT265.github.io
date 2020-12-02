package org.jabref.gui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class JabRefMainTest extends ApplicationTest {

    private JabRefMain main;
    @Override
    public void start(Stage stage) throws Exception {
        main = new JabRefMain();
        main.start(stage);
    }

    @Test
    public void test()
    {
        main.stop();
    }
}
