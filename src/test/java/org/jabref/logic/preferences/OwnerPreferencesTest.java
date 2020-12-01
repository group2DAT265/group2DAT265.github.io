package org.jabref.logic.preferences;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OwnerPreferencesTest {

    private OwnerPreferences op;
    boolean useOwner;
    String defaultOwner = "";
    boolean overwriteOwner;

    @Test
    void constructorTest() {
        op = spy(new OwnerPreferences(useOwner,defaultOwner,overwriteOwner));
    }

    @Test
    void isOwnerTest() {
        boolean res = op.isUseOwner();
        assertTrue(res);
    }

    @Test
    void isOverwriteOwnerTest() {
        boolean result = op.isOverwriteOwner();
        assertTrue(result);
    }

    @Test
    void getDefaultOwnerTest() {
        assertEquals(defaultOwner, op.getDefaultOwner());
    }
}
