package org.jabref.logic.util.io;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.entry.BibEntry;
import org.jabref.preferences.FilePreferences;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseFileLookupTest {
    private DatabaseFileLookup databaseFileLookup;

    @BeforeEach
    void setup() {
        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(new BibEntry());
        this.databaseFileLookup = new DatabaseFileLookup(
                bibDatabaseContext,
                new FilePreferences("TestUser",
                        new SimpleStringProperty("").getValue(),
                        false,
                        new SimpleStringProperty().getValue(),
                        new SimpleStringProperty().getValue(),
                        false,
                        false,
                        false));
    }

    @Test
    void testLookupDatabase() {
        assertFalse(databaseFileLookup.lookupDatabase(new File("foo/bar")));
    }
}
