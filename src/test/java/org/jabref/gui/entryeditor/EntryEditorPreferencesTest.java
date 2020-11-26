package org.jabref.gui.entryeditor;

import org.jabref.model.entry.field.Field;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.spy;

public class EntryEditorPreferencesTest {

    Map<String, Set<Field>> entryEditorTabList;
    boolean shouldOpenOnNewEntry;
    boolean shouldShowRecommendationsTab;
    boolean isMrdlibAccepted;
    boolean shouldShowLatexCitationsTab;
    boolean showSourceTabByDefault;
    boolean enableValidation;

    private EntryEditorPreferences eep;

    @Test
    void test() {
        eep = spy(new EntryEditorPreferences(entryEditorTabList, shouldOpenOnNewEntry, shouldShowRecommendationsTab, isMrdlibAccepted,
                shouldShowLatexCitationsTab, showSourceTabByDefault, enableValidation));
    }

    @Test
    void shouldOpenOnNewEntryTest() {
        Mockito.when(eep.shouldOpenOnNewEntry()).thenReturn(true);
    }

    @Test
    void shouldShowRecommendationsTabTest() {
        Mockito.when(eep.shouldShowRecommendationsTab()).thenReturn(true);
    }

    @Test
    void isMrdlibAcceptedTest() {
        Mockito.when(eep.isMrdlibAccepted()).thenReturn(true);
    }

    @Test
    void showSourceTabByDefaultTest() {
        Mockito.when(eep.showSourceTabByDefault()).thenReturn(true);
    }

    @Test
    void shouldShowLatexCitationsTabTest() {
        Mockito.when(eep.shouldShowLatexCitationsTab()).thenReturn(true);
    }

    @Test
    void isEnableValidationTest() {
        Mockito.when(eep.isEnableValidation()).thenReturn(true);
    }
}
