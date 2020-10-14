package org.jabref.logic.exporter;

import java.util.List;

import org.jabref.logic.layout.format.GetOpenOfficeType;
import org.jabref.logic.layout.format.RemoveBrackets;
import org.jabref.logic.layout.format.RemoveWhitespace;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.Field;
import org.jabref.model.entry.field.InternalField;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.field.UnknownField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

abstract class OOShared {

    public static void buildTable(Document result, Element row, List<BibEntry> entries, Element table) {
        addTableCell(result, row, "Type");
        addTableCell(result, row, "ISBN");
        addTableCell(result, row, "Identifier");
        addTableCell(result, row, "Author");
        addTableCell(result, row, "Title");
        addTableCell(result, row, "Journal");
        addTableCell(result, row, "Volume");
        addTableCell(result, row, "Number");
        addTableCell(result, row, "Month");
        addTableCell(result, row, "Pages");
        addTableCell(result, row, "Year");
        addTableCell(result, row, "Address");
        addTableCell(result, row, "Note");
        addTableCell(result, row, "URL");
        addTableCell(result, row, "Booktitle");
        addTableCell(result, row, "Chapter");
        addTableCell(result, row, "Edition");
        addTableCell(result, row, "Series");
        addTableCell(result, row, "Editor");
        addTableCell(result, row, "Publisher");
        addTableCell(result, row, "ReportType");
        addTableCell(result, row, "Howpublished");
        addTableCell(result, row, "Institution");
        addTableCell(result, row, "Organization");
        addTableCell(result, row, "School");
        addTableCell(result, row, "Annote");
        addTableCell(result, row, "Assignee");
        addTableCell(result, row, "Day");
        addTableCell(result, row, "Dayfiled");
        addTableCell(result, row, "Monthfiled");
        addTableCell(result, row, "Yearfiled");
        addTableCell(result, row, "Language");
        addTableCell(result, row, "Nationality");
        addTableCell(result, row, "Revision");
        addTableCell(result, row, "Custom1");
        addTableCell(result, row, "Custom2");
        addTableCell(result, row, "Custom3");
        addTableCell(result, row, "Custom4");
        addTableCell(result, row, "Custom5");
        table.appendChild(row);

        for (BibEntry e : entries) {
            row = result.createElement("table:table-row");
            addTableCell(result, row, new GetOpenOfficeType().format(e.getType().getName()));
            addTableCell(result, row, getField(e, StandardField.ISBN));
            addTableCell(result, row, getField(e, InternalField.KEY_FIELD));
            addTableCell(result, row, getField(e, StandardField.AUTHOR)); // new AuthorLastFirst().format(getField(e, StandardField.AUTHOR_FIELD)));
            addTableCell(result, row, new RemoveWhitespace().format(new RemoveBrackets().format(getField(e, StandardField.TITLE))));
            addTableCell(result, row, getField(e, StandardField.JOURNAL));
            addTableCell(result, row, getField(e, StandardField.VOLUME));
            addTableCell(result, row, getField(e, StandardField.NUMBER));
            addTableCell(result, row, getField(e, StandardField.MONTH));
            addTableCell(result, row, getField(e, StandardField.PAGES));
            addTableCell(result, row, getField(e, StandardField.YEAR));
            addTableCell(result, row, getField(e, StandardField.ADDRESS));
            addTableCell(result, row, getField(e, StandardField.NOTE));
            addTableCell(result, row, getField(e, StandardField.URL));
            addTableCell(result, row, getField(e, StandardField.BOOKTITLE));
            addTableCell(result, row, getField(e, StandardField.CHAPTER));
            addTableCell(result, row, getField(e, StandardField.EDITION));
            addTableCell(result, row, getField(e, StandardField.SERIES));
            addTableCell(result, row, getField(e, StandardField.EDITOR)); // new AuthorLastFirst().format(getField(e, StandardField.EDITOR_FIELD)));
            addTableCell(result, row, getField(e, StandardField.PUBLISHER));
            addTableCell(result, row, getField(e, new UnknownField("reporttype")));
            addTableCell(result, row, getField(e, StandardField.HOWPUBLISHED));
            addTableCell(result, row, getField(e, StandardField.INSTITUTION));
            addTableCell(result, row, getField(e, StandardField.ORGANIZATION));
            addTableCell(result, row, getField(e, StandardField.SCHOOL));
            addTableCell(result, row, getField(e, StandardField.ANNOTE));
            addTableCell(result, row, getField(e, StandardField.ASSIGNEE));
            addTableCell(result, row, getField(e, StandardField.DAY));
            addTableCell(result, row, getField(e, StandardField.DAYFILED));
            addTableCell(result, row, getField(e, StandardField.MONTHFILED));
            addTableCell(result, row, getField(e, StandardField.YEARFILED));
            addTableCell(result, row, getField(e, StandardField.LANGUAGE));
            addTableCell(result, row, getField(e, StandardField.NATIONALITY));
            addTableCell(result, row, getField(e, StandardField.REVISION));
            addTableCell(result, row, "");
            addTableCell(result, row, "");
            addTableCell(result, row, "");
            addTableCell(result, row, "");
            addTableCell(result, row, "");
            table.appendChild(row);
        }
    }

    private static String getField(BibEntry e, Field field) {

        return e.getField(field).orElse("");

    }

    private static void addTableCell(Document doc, Element parent, String content) {
        Element cell = doc.createElement("table:table-cell");
        Element text = doc.createElement("text:p");
        Text textNode = doc.createTextNode(content);
        text.appendChild(textNode);
        // text.setTextContent(content);
        cell.appendChild(text);
        parent.appendChild(cell);
    }
}
