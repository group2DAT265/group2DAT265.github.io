package org.jabref.gui.edit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jabref.gui.ClipBoardManager;
import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.StandardActions;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.InternalField;
import org.jabref.model.entry.field.StandardField;
import org.jabref.preferences.PreferencesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CopyMoreActionTest {
    private CopyMoreAction copyMoreAction;
    private DialogService dialogService;
    private StateManager stateManager;
    private ClipBoardManager clipBoardManager;
    private PreferencesService preferencesService;

    @BeforeEach
    public void setUp() {
        preferencesService = mock(PreferencesService.class);
        dialogService = mock(DialogService.class);
        clipBoardManager = mock(ClipBoardManager.class);
        stateManager = spy(new StateManager());
    }

    @Test
    public void emptyDatabaseAndNoSelectedEntries() {
        when(stateManager.getActiveDatabase()).thenReturn(Optional.empty());

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_TITLE, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService, never()).notify(anyString());
    }

    @Test
    public void emptyDatabase() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(StandardField.TITLE, "TestTitle1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(StandardField.TITLE, "TestTitle2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        when(stateManager.getActiveDatabase()).thenReturn(Optional.empty());

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_TITLE, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService, never()).notify(anyString());
    }

    @Test
    public void noSelectedEntries() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(StandardField.TITLE, "TestTitle1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(StandardField.TITLE, "TestTitle2");
        bibEntries.add(bibEntry2);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_TITLE, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService, never()).notify(anyString());
    }

    @Test
    public void unKnownAction() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(InternalField.KEY_FIELD, "Key1");
        bibEntry1.setField(StandardField.TITLE, "TestTitle1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(InternalField.KEY_FIELD, "Key2");
        bibEntry2.setField(StandardField.TITLE, "TestTitle2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.ABOUT, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService, never()).notify(anyString());
    }

    @Test
    public void copyTitleAllEntriesHaveTitles() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(StandardField.TITLE, "TestTitle1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(StandardField.TITLE, "TestTitle2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_TITLE, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager).setContent(anyString());
        verify(dialogService).notify(anyString());
    }

    @Test
    public void copyTitleNoEntriesHaveTitles() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_TITLE, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService).notify(Localization.lang("None of the selected entries have titles."));
    }

    @Test
    public void copyTitleSomeEntriesHaveTitles() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(StandardField.TITLE, "TestTitle1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_TITLE, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager).setContent(anyString());
        verify(dialogService).notify(Localization.lang("Warning: 1 out of 2 entries have undefined title."));
    }

    @Test
    public void copyKeyAllEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(InternalField.KEY_FIELD, "Key1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(InternalField.KEY_FIELD, "Key2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_KEY, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager).setContent(anyString());
        verify(dialogService).notify(anyString());
    }

    @Test
    public void copyKeyNoEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_KEY, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService).notify(anyString());
    }

    @Test
    public void copyKeySomeEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(InternalField.KEY_FIELD, "Key1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_KEY, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager).setContent(anyString());
        verify(dialogService).notify(anyString());
    }

    @Test
    public void copyCiteKeyNoEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_CITE_KEY, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setContent(anyString());
        verify(dialogService).notify(anyString());
    }


    @Test
    public void copyKeyAndLinkAllEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(InternalField.KEY_FIELD, "Key1");
        bibEntry1.setField(StandardField.URL, "foo/bar/1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(InternalField.KEY_FIELD, "Key2");
        bibEntry2.setField(StandardField.URL, "foo/bar/2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_KEY_AND_LINK, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager).setHtmlContent(anyString());
        verify(dialogService).notify(anyString());
    }

    @Test
    public void copyKeyAndLinkNoEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(StandardField.URL, "foo/bar/1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(StandardField.URL, "foo/bar/2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_KEY_AND_LINK, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager, never()).setHtmlContent(anyString());
        verify(dialogService).notify(anyString());
    }

    @Test
    public void copyKeyAndLinkSomeEntriesHaveKeys() {
        List<BibEntry> bibEntries = new ArrayList<BibEntry>();

        BibEntry bibEntry1 = new BibEntry();
        bibEntry1.setField(InternalField.KEY_FIELD, "Key1");
        bibEntry1.setField(StandardField.URL, "foo/bar/1");
        bibEntries.add(bibEntry1);

        BibEntry bibEntry2 = new BibEntry();
        bibEntry2.setField(StandardField.URL, "foo/bar/2");
        bibEntries.add(bibEntry2);

        stateManager.setSelectedEntries(bibEntries);

        BibDatabaseContext bibDatabaseContext = new BibDatabaseContext();
        bibDatabaseContext.getDatabase().insertEntry(bibEntry1);
        bibDatabaseContext.getDatabase().insertEntry(bibEntry2);
        when(stateManager.getActiveDatabase()).thenReturn(java.util.Optional.of(bibDatabaseContext));

        copyMoreAction = spy(new CopyMoreAction(StandardActions.COPY_KEY_AND_LINK, dialogService, stateManager, clipBoardManager, preferencesService));
        copyMoreAction.execute();

        verify(clipBoardManager).setHtmlContent(anyString());
        verify(dialogService).notify(anyString());
    }
}
