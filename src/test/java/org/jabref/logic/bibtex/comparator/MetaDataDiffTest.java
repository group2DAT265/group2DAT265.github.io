package org.jabref.logic.bibtex.comparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.groups.AbstractGroup;
import org.jabref.model.groups.GroupHierarchyType;
import org.jabref.model.groups.GroupTreeNode;
import org.jabref.model.metadata.ContentSelector;
import org.jabref.model.metadata.MetaData;
import org.jabref.preferences.JabRefPreferences;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
