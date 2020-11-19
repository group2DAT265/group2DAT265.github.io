package org.jabref.gui.actions;

import org.jabref.gui.StateManager;
import org.jabref.gui.bibtexextractor.ExtractBibtexAction;
import org.jabref.gui.keyboard.KeyBindingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;

public class JabRefActionTest {

    private JabRefAction jabRefAction;
    private StandardActions standardActions;
    private KeyBindingRepository keyBindingRepository;
    private ExtractBibtexAction extractBibtexAction;
    private StateManager stateManager;

    @BeforeEach
    public void setUp() {
        standardActions = StandardActions.NEW_ENTRY_FROM_PLAIN_TEXT;

        keyBindingRepository = spy(new KeyBindingRepository());
        stateManager = spy(new StateManager());
        extractBibtexAction = spy(new ExtractBibtexAction(stateManager));
    }

    @Test
    public void testFirstConstructor() {
        jabRefAction = spy(new JabRefAction(standardActions, keyBindingRepository));
    }

    @Test
    public void testSecondConstructor() {
        jabRefAction = spy(new JabRefAction(standardActions, extractBibtexAction, keyBindingRepository));
    }
}
