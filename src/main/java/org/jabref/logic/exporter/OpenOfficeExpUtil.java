package org.jabref.logic.exporter;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jabref.model.entry.BibEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OpenOfficeExpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OOCalcDatabase.class);

    public static Document getDOMrepresentation(List<BibEntry> entries, boolean includeSpreadsheet) {
        Document result = null;
        try {
            DocumentBuilder dbuild = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            result = dbuild.newDocument();
            Element collection = result.createElement("office:document-content");
            // collection.setAttribute("xmlns", "http://openoffice.org/2000/office");
            collection.setAttribute("xmlns:office", "urn:oasis:names:tc:opendocument:xmlns:office:1.0");
            collection.setAttribute("xmlns:style", "urn:oasis:names:tc:opendocument:xmlns:style:1.0");
            collection.setAttribute("xmlns:text", "urn:oasis:names:tc:opendocument:xmlns:text:1.0");
            collection.setAttribute("xmlns:table", "urn:oasis:names:tc:opendocument:xmlns:table:1.0");
            collection.setAttribute("xmlns:meta", "urn:oasis:names:tc:opendocument:xmlns:meta:1.0");
            collection.setAttribute("office:version", "1.0");
            collection.setAttribute("xmlns:fo", "urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0");
            collection.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
            Element el = result.createElement("office:scripts");
            collection.appendChild(el);

            el = result.createElement("office:automatic-styles");
            Element el2 = result.createElement("style:style");
            el2.setAttribute("style:name", "ro1");
            el2.setAttribute("style:family", "table-row");
            Element el3 = result.createElement("style.table-row-properties");
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

            if (includeSpreadsheet) {
                Element spreadsheet = result.createElement("office:spreadsheet");
                body.appendChild(spreadsheet);
            }

            Element row = result.createElement("table:table-row");
            row.setAttribute("table.style-name", "ro1");
            OOShared.buildTable(result, row, entries, table);
<<<<<<< HEAD
=======
            if (includeSpreadsheet) {
                collection.appendChild(body);
            }
>>>>>>> 10190461881cc4e74124b57288fb3efaab645dec

            result.appendChild(collection);
        } catch (Exception e) {
            LOGGER.warn("Exception caught...", e);
        }
        return result;
    }

}
