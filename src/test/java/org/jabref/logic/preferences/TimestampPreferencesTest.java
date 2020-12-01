package org.jabref.logic.preferences;

import org.jabref.model.entry.field.Field;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimestampPreferencesTest {

    boolean useTimestamps;
    boolean updateTimestamp;
    Field timestampField;
    String timestampFormat = "";
    boolean overwriteTimestamp;

    private TimestampPreferences tp;

    @Test
    void constructorTest() {
        tp = spy(new TimestampPreferences(useTimestamps, updateTimestamp, timestampField, timestampFormat, overwriteTimestamp));
    }

    @Test
    void isUseTimestampsTest() {
        boolean timeStamp = tp.isUseTimestamps();
        assertTrue(timeStamp);
    }

    @Test
    void isUpdateTimestampTest() {
        boolean updatedTimeStamp = tp.isUpdateTimestamp();
        assertTrue(updatedTimeStamp);
    }

    @Test
    void isOverwriteTimestampTest() {
        boolean overwriteTimestamp = tp.isUpdateTimestamp();
        assertTrue(overwriteTimestamp);
    }

    @Test
    void includeTimestampsTest() {
        boolean includeTimestamp = tp.includeTimestamps();
        assertTrue(includeTimestamp);
    }

    @Test
    void getTimestampFormatTest() {
        assertEquals(timestampFormat, tp.getTimestampFormat());
    }

    @Test
    void getTimestampFieldTest() {
        assertEquals(timestampField, tp.getTimestampField());
    }
}
