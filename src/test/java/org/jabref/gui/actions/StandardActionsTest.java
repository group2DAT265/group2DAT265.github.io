package org.jabref.gui.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardActionsTest {

    private StandardActions action;

    @BeforeEach
    void setUp() {
        action = StandardActions.COPY_CITATION_TEXT;
    }

    @Test
    void verifyGetText() {
        assertEquals("Text", action.getText());

    }
}
