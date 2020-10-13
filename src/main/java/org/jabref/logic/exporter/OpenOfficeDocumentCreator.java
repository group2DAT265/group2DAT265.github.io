package org.jabref.logic.exporter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.jabref.logic.util.StandardFileType;
import org.jabref.model.database.BibDatabase;
import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.entry.BibEntry;

public class OpenOfficeDocumentCreator extends OpenOfficeCreator {

    /**
     * Creates a new instance of OpenOfficeDocumentCreator
     */
    public OpenOfficeDocumentCreator() {
        super("oocalc", "Old OpenOffice/LibreOffice Calc format", StandardFileType.SXC);
    }

    private static void storeOpenOfficeFile(Path file, InputStream source) throws Exception {
        try (ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(file)))) {
            ZipEntry zipEntry = new ZipEntry("content.xml");
            out.putNextEntry(zipEntry);
            int c;
            while ((c = source.read()) >= 0) {
                out.write(c);
            }
            out.closeEntry();

            // Add manifest (required for OOo 2.0), "meta.xml", "mimetype" files. These are in the
            // resource/openoffice directory, and are copied verbatim into the zip file.
            addResourceFile("meta.xml", "/resource/openoffice/meta.xml", out);
            addResourceFile("mimetype", "/resource/openoffice/mimetype", out);
            addResourceFile("META-INF/manifest.xml", "/resource/openoffice/manifest.xml",
                    out);
        }
    }

    private static void exportOpenOfficeCalc(Path file, BibDatabase database, List<BibEntry> entries) throws Exception {
        // First store the xml formatted content to a temporary file.
        File tmpFile = File.createTempFile("oocalc", null);
        exportOpenDocumentOrSpreadsheet(tmpFile, database, entries);

        // Then add the content to the zip file:
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(tmpFile))) {
            storeOpenOfficeFile(file, in);
        }

        // Delete the temporary file:
        if (!tmpFile.delete()) {
            LOGGER.info("Cannot delete temporary export file");
        }
    }

    @Override
    public void export(final BibDatabaseContext databaseContext, final Path file,
                       final Charset encoding, List<BibEntry> entries) throws Exception {
        Objects.requireNonNull(databaseContext);
        Objects.requireNonNull(entries);
        if (!entries.isEmpty()) { // Do not export if no entries
            exportOpenOfficeCalc(file, databaseContext.getDatabase(), entries);
        }
    }
}
