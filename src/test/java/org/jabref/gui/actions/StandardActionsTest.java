package org.jabref.gui.actions;

import java.util.Optional;

import org.jabref.gui.icon.IconTheme;
import org.jabref.gui.icon.JabRefIcon;
import org.jabref.gui.keyboard.KeyBinding;
import org.jabref.logic.l10n.Localization;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardActionsTest {

    private StandardActions action;

    @BeforeEach
    void setUp() {
        action = StandardActions.SAVE_ALL;
    }

    @Test
    void verifyGetText() {
        assertEquals(Localization.lang("Save all"), action.getText());
    }

    @Test
    void verifyGetDescription() {
    	assertEquals(Localization.lang("Save all open libraries"), action.getDescription());
    }

    @Test
    void verifyGetKeyBinding() {
        Optional<KeyBinding> keyBinding = Optional.of(KeyBinding.SAVE_ALL);
        assertEquals(keyBinding, action.getKeyBinding());
    }

    @Test
    void verifyGetIcon() {
        Optional<JabRefIcon> icon = Optional.of(IconTheme.JabRefIcons.SAVE_ALL);
        assertEquals(icon, action.getIcon());
    }
}
