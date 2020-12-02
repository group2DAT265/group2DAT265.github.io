package org.jabref.gui.actions;

import de.saxsys.mvvmfx.utils.commands.Command;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.action.ActionUtils;
import org.jabref.gui.keyboard.KeyBindingRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionFactoryTest {

    KeyBindingRepository keyBindingRepository;
    ActionFactory af;
    MenuItem menuItem = new MenuItem();
    Command command;
    Action action;
    boolean selected;
    CheckMenuItem checkMenuItem = ActionUtils.createCheckMenuItem(new JabRefAction(action, command, keyBindingRepository, Sources.FromMenu));
    Menu menu = ActionUtils.createMenu(new JabRefAction(action, keyBindingRepository));

    @Test
    void constructorTest() {
        af = spy(new ActionFactory(keyBindingRepository));
    }

    @Test
    void createMenuItemTest() {
        af.configureMenuItem(action, command, menuItem);
        assertEquals(menuItem, af.createMenuItem(action, command));
    }

    @Test
    void createCheckMenuItemTest() {
        checkMenuItem.setSelected(selected);
        assertEquals(checkMenuItem, af.createCheckMenuItem(action, command,selected));
    }

    @Test
    void createMenuTest() {
        assertEquals(menu, af.createMenu(action));
    }
}
