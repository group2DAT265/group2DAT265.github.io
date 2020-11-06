package org.jabref.logic.exporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jabref.logic.bibtex.comparator.FieldComparator;
import org.jabref.logic.bibtex.comparator.FieldComparatorStack;
import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.InternalField;
import org.jabref.model.entry.field.StandardField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

class OpenDocumentRepresentation {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenDocumentRepresentation.class);
    private final List<BibEntry> entries;

    public OpenDocumentRepresentation(BibDatabase database, List<BibEntry> entries) {
        // Make a list of comparators for sorting the entries:
        List<FieldComparator> comparators = new ArrayList<>();
        comparators.add(new FieldComparator(StandardField.AUTHOR));
        comparators.add(new FieldComparator(StandardField.YEAR));
        comparators.add(new FieldComparator(InternalField.KEY_FIELD));
        // Use glazed lists to get a sorted view of the entries:
        List<BibEntry> entryList = new ArrayList<>();

        // Set up a list of all entries, if entries==null, or the entries in the given list
        if (entries == null) {
            entryList.addAll(database.getEntries());
        } else {
            entryList.addAll(entries);
        }

        Collections.sort(entryList, new FieldComparatorStack<>(comparators));
        this.entries = entryList;
    }

    public Document getDOMrepresentation() {
        return OpenOfficeExpUtil.getDOMrepresentation(entries, true);
    }
}
