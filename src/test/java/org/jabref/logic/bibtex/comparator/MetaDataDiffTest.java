package org.jabref.logic.bibtex.comparator;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jabref.logic.citationkeypattern.AbstractCitationKeyPattern;
import org.jabref.logic.citationkeypattern.DatabaseCitationKeyPattern;
import org.jabref.logic.citationkeypattern.GlobalCitationKeyPattern;
import org.jabref.logic.cleanup.FieldFormatterCleanup;
import org.jabref.logic.cleanup.FieldFormatterCleanups;
import org.jabref.logic.formatter.casechanger.LowerCaseFormatter;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.database.BibDatabaseMode;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.StandardEntryType;
import org.jabref.model.groups.AbstractGroup;
import org.jabref.model.groups.GroupHierarchyType;
import org.jabref.model.groups.GroupTreeNode;
import org.jabref.model.metadata.ContentSelector;
import org.jabref.model.metadata.MetaData;
import org.jabref.model.metadata.SaveOrderConfig;
import org.jabref.preferences.JabRefPreferences;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class MetaDataDiffTest {

    private JabRefPreferences prefs = JabRefPreferences.getInstance();

    @Test
    public void compareWithSameContentSelectorsDoesNotReportAnyDiffs() throws Exception {
        MetaData one = new MetaData();
        one.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "second"));
        MetaData two = new MetaData();
        two.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "second"));

        assertEquals(Optional.empty(), MetaDataDiff.compare(one, two));
    }

    @Test
    public void compareWithDifferentContentSelectorsReportDiffs() throws Exception {
        MetaData one = new MetaData();
        one.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "second"));
        MetaData two = new MetaData();
        two.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "notSecond"));

        assertNotEquals(Optional.empty(), MetaDataDiff.compare(one, two));
    }

    @Test
    public void ensureGetNewMetaDataReturnsNewMetaData() throws Exception {
        MetaData originalMetaData = new MetaData();
        originalMetaData.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "second"));
        MetaData newMetaData = new MetaData();
        newMetaData.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "notSecond"));
        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        assertEquals(newMetaData, metaDataDiff.getNewMetaData());
    }

    @Test
    public void ensureGetGroupDifferencesReturnsGroupDiff() throws Exception {
        MetaData originalMetaData = new MetaData();
        originalMetaData.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "second"));
        MetaData newMetaData = new MetaData();
        newMetaData.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "notSecond"));
        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        Optional<GroupDiff> groupDiff = GroupDiff.compare(originalMetaData, newMetaData);
        assertEquals(groupDiff, metaDataDiff.getGroupDifferences());
    }

    @Test
    public void compareTwoMetaDataWithDifferentProtections() throws Exception {
        MetaData originalMetaData = new MetaData();
        originalMetaData.markAsProtected();
        MetaData newMetaData = new MetaData();
        newMetaData.markAsNotProtected();
        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Library protection"));
        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentContentSelectors() throws Exception {
        MetaData originalMetaData = new MetaData();
        originalMetaData.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "second"));
        MetaData newMetaData = new MetaData();
        newMetaData.addContentSelector(new ContentSelector(StandardField.AUTHOR, "first", "notSecond"));
        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Content selectors"));
        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentGroups() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        GroupTreeNode targetRootGroup = new GroupTreeNode(new TestGroup("targetGroup", GroupHierarchyType.INDEPENDENT));
        GroupTreeNode otherRootGroup = new GroupTreeNode(new TestGroup("otherGroup", GroupHierarchyType.INCLUDING));

        originalMetaData.setGroups(targetRootGroup);
        originalMetaData.setGroups(otherRootGroup);

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Modified groups tree"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentEncoding() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        originalMetaData.setEncoding(Charset.forName("UTF-8"));
        newMetaData.setEncoding(Charset.forName("UTF-16"));

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Library encoding"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentSaveOrderConfigurations() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        SaveOrderConfig saveOrderConfig = new SaveOrderConfig();
        SaveOrderConfig saveOrderConfigOther = new SaveOrderConfig();
        saveOrderConfigOther.setSaveInSpecifiedOrder();


        originalMetaData.setSaveOrderConfig(saveOrderConfig);
        newMetaData.setSaveOrderConfig(saveOrderConfigOther);

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Save sort order"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentCiteKeyPatterns() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        AbstractCitationKeyPattern pattern = new DatabaseCitationKeyPattern(mock(GlobalCitationKeyPattern.class));
        pattern.setDefaultValue("test");
        pattern.addCitationKeyPattern(StandardEntryType.ARTICLE, "articleTest");
        originalMetaData.setCiteKeyPattern(pattern);

        AbstractCitationKeyPattern patternNew = new DatabaseCitationKeyPattern(mock(GlobalCitationKeyPattern.class));
        patternNew.setDefaultValue("testing");
        patternNew.addCitationKeyPattern(StandardEntryType.ARTICLE, "articleTest");
        newMetaData.setCiteKeyPattern(patternNew);

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Key patterns"));
        expected.add(Localization.lang("Default pattern"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentUserFileDirectories() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        originalMetaData.setUserFileDirectory("user", "path");
        newMetaData.setUserFileDirectory("userNew", "pathNew");

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("User-specific file directory"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentLatexFileDirectories() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        originalMetaData.setLatexFileDirectory("user", Paths.get("/tmp/foo"));
        newMetaData.setLatexFileDirectory("user", Paths.get("/tmp/foo/bar"));

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("LaTeX file directory"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentSaveActions() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        FieldFormatterCleanups saveActions = new FieldFormatterCleanups(true, Collections.singletonList(new FieldFormatterCleanup(StandardField.TITLE, new LowerCaseFormatter())));
        FieldFormatterCleanups saveActionsNew = new FieldFormatterCleanups(true, Collections.singletonList(new FieldFormatterCleanup(StandardField.AUTHOR, new LowerCaseFormatter())));

        originalMetaData.setSaveActions(saveActions);
        newMetaData.setSaveActions(saveActionsNew);

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Save actions"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentModes() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        originalMetaData.setMode(BibDatabaseMode.BIBTEX);
        newMetaData.setMode(BibDatabaseMode.BIBLATEX);

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("Library mode"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    @Test
    public void compareTwoMetaDataWithDifferentDefaultFileDirectories() throws Exception {
        MetaData originalMetaData = new MetaData();
        MetaData newMetaData = new MetaData();

        originalMetaData.setDefaultFileDirectory("tmp/foo");
        newMetaData.setDefaultFileDirectory("tmp/foo/bar");

        MetaDataDiff metaDataDiff = MetaDataDiff.compare(originalMetaData, newMetaData).get();
        
        List<String> expected = new ArrayList<>();
        expected.add(Localization.lang("General file directory"));

        assertLinesMatch(expected, metaDataDiff.getDifferences(prefs));
    }

    static class TestGroup extends AbstractGroup {

        protected TestGroup(String name, GroupHierarchyType context) {
            super(name, context);
        }

        @Override
        public boolean contains(BibEntry entry) {
            return false;
        }

        @Override
        public boolean isDynamic() {
            return false;
        }

        @Override
        public AbstractGroup deepCopy() {
            return null;
        }
    }


}
