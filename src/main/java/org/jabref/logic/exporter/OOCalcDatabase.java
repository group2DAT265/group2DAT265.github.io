package org.jabref.logic.exporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jabref.logic.bibtex.comparator.FieldComparator;
import org.jabref.logic.bibtex.comparator.FieldComparatorStack;
import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.InternalField;
import org.jabref.model.entry.field.StandardField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class OOCalcDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(OOCalcDatabase.class);
    private final List<BibEntry> entries;

    public OOCalcDatabase(BibDatabase bibtex, List<BibEntry> entries) {
        // Make a list of comparators for sorting the entries:
        List<FieldComparator> comparators = new ArrayList<>();
        comparators.add(new FieldComparator(StandardField.AUTHOR));
        comparators.add(new FieldComparator(StandardField.YEAR));
        comparators.add(new FieldComparator(InternalField.KEY_FIELD));
        // Use glazed lists to get a sorted view of the entries:
        List<BibEntry> entryList = new ArrayList<>();
        // Set up a list of all entries, if keySet==null, or the entries whose
        // ids are in keySet, otherwise:
        if (entries == null) {
            entryList.addAll(bibtex.getEntries());
        } else {
            entryList.addAll(entries);
        }
        Collections.sort(entryList, new FieldComparatorStack<>(comparators));
        this.entries = entryList;
    }

    public Document getDOMrepresentation() {
        Document result = null;
        try {
            DocumentBuilder dbuild = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            result = dbuild.newDocument();
            Element collection = result.createElement("office:document-content");
            // collection.setAttribute("xmlns", "http://openoffice.org/2000/office");
            collection.setAttribute("xmlns:office", "http://openoffice.org/2000/office");
            collection.setAttribute("xmlns:style", "http://openoffice.org/2000/style");
            collection.setAttribute("xmlns:text", "http://openoffice.org/2000/text");
            collection.setAttribute("xmlns:table", "http://openoffice.org/2000/table");
            collection.setAttribute("xmlns:office:class", "spreadsheet");
            collection.setAttribute("xmlns:office:version", "1.0");
            collection.setAttribute("xmlns:fo", "http://www.w3.org/1999/XSL/Format");
            Element el = result.createElement("office:script");
            collection.appendChild(el);

            el = result.createElement("office:automatic-styles");
            Element el2 = result.createElement("style:style");
            el2.setAttribute("style:name", "ro1");
            el2.setAttribute("style:family", "table-row");
            Element el3 = result.createElement("style.properties");
            el3.setAttribute("style:row-height", "0.1681inch");
            el3.setAttribute("fo:break-before", "auto");
            el3.setAttribute("style:use-optimal-row-height", "true");
            el2.appendChild(el3);
            el.appendChild(el2);
            el2 = result.createElement("style:style");
            el2.setAttribute("style:name", "ta1");
            el2.setAttribute("style:family", "table");
            el2.setAttribute("style:master-page-name", "Default");
            el3 = result.createElement("style:properties");
            el3.setAttribute("table:display", "true");
            el2.appendChild(el3);
            el.appendChild(el2);
            collection.appendChild(el);

            Element body = result.createElement("office:body");
            Element table = result.createElement("table:table");
            table.setAttribute("table:name", "biblio");
            table.setAttribute("table.style-name", "ta1");

            Element row = result.createElement("table:table-row");
            row.setAttribute("table.style-name", "ro1");
            OOShared.buildTable(result, row, entries, table);
            body.appendChild(table);
            collection.appendChild(body);

            result.appendChild(collection);
        } catch (Exception e) {
            LOGGER.warn("Exception caught...", e);
        }
        return result;
    }
}
