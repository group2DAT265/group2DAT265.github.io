package org.jabref.logic.importer.fileformat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.jabref.logic.importer.Importer;
import org.jabref.logic.importer.ParserResult;
import org.jabref.logic.util.StandardFileType;
import org.jabref.model.entry.AuthorList;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.Field;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.EntryType;
import org.jabref.model.entry.types.EntryTypeFactory;
import org.jabref.model.entry.types.StandardEntryType;

/**
 * Imports a SilverPlatter exported file. This is a poor format to parse, so it currently doesn't handle everything correctly.
 */
public class SilverPlatterImporter extends Importer {

    private static final Pattern START_PATTERN = Pattern.compile("Record.*INSPEC.*");

    @Override
    public String getName() {
        return "SilverPlatter";
    }

    @Override
    public StandardFileType getFileType() {
        return StandardFileType.SILVER_PLATTER;
    }

    @Override
    public String getDescription() {
        return "Imports a SilverPlatter exported file.";
    }

    @Override
    public boolean isRecognizedFormat(BufferedReader reader) throws IOException {
        // This format is very similar to Inspec, so we have a two-fold strategy:
        // If we see the flag signaling that it is an Inspec file, return false.
        // This flag should appear above the first entry and prevent us from
        // accepting the Inspec format. Then we look for the title entry.
        String str;
        while ((str = reader.readLine()) != null) {

            if (START_PATTERN.matcher(str).find()) {
                return false; // This is an Inspec file, so return false.
            }

            if ((str.length() >= 5) && "TI:  ".equals(str.substring(0, 5))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ParserResult importDatabase(BufferedReader reader) throws IOException {
        List<BibEntry> bibitems = new ArrayList<>();
        boolean isChapter = false;
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = reader.readLine()) != null) {
            if (str.length() < 2) {
                sb.append("__::__").append(str);
            } else {
                sb.append("__NEWFIELD__").append(str);
            }
        }
        String[] entries = sb.toString().split("__::__");
        EntryType type = StandardEntryType.MISC;
        Map<Field, String> h = new HashMap<>();
        for (String entry : entries) {
            if (entry.trim().length() < 6) {
                continue;
            }
            h.clear();
            String[] fields = entry.split("__NEWFIELD__");
            for (String field : fields) {
                if (field.length() < 6) {
                    continue;
                }
                String f3 = field.substring(0, 2);
                String frest = field.substring(5);
                if ("TI".equals(f3)) {
                    h.put(StandardField.TITLE, frest);
                } else if ("AU".equals(f3)) {
                    if (frest.trim().endsWith("(ed)")) {
                        String ed = frest.trim();
                        ed = ed.substring(0, ed.length() - 4);
                        h.put(StandardField.EDITOR,
                                AuthorList.fixAuthorLastNameFirst(ed.replace(",-", ", ").replace(";", " and ")));
                    } else {
                        h.put(StandardField.AUTHOR,
                                AuthorList.fixAuthorLastNameFirst(frest.replace(",-", ", ").replace(";", " and ")));
                    }
                } else if ("AB".equals(f3)) {
                    h.put(StandardField.ABSTRACT, frest);
                } else if ("DE".equals(f3)) {
                    String kw = frest.replace("-;", ",").toLowerCase(Locale.ROOT);
                    h.put(StandardField.KEYWORDS, kw.substring(0, kw.length() - 1));
                } else if ("SO".equals(f3)) {
                    int m = frest.indexOf('.');
                    if (m >= 0) {
                        String jr = frest.substring(0, m);
                        h.put(StandardField.JOURNAL, jr.replace("-", " "));
                        frest = frest.substring(m);
                        m = frest.indexOf(';');
                        if (m >= 5) {
                            String yr = frest.substring(m - 5, m).trim();
                            h.put(StandardField.YEAR, yr);
                            frest = frest.substring(m);
                            m = frest.indexOf(':');
                            int issueIndex = frest.indexOf('(');
                            int endIssueIndex = frest.indexOf(')');
                            if (m >= 0) {
                                String pg = frest.substring(m + 1).trim();
                                h.put(StandardField.PAGES, pg);
                                h.put(StandardField.VOLUME, frest.substring(1, issueIndex).trim());
                                h.put(StandardField.ISSUE, frest.substring(issueIndex + 1, endIssueIndex).trim());
                            }
                        }
                    }
                } else if ("PB".equals(f3)) {
                    int m = frest.indexOf(':');
                    if (m >= 0) {
                        String jr = frest.substring(0, m);
                        h.put(StandardField.PUBLISHER, jr.replace("-", " ").trim());
                        frest = frest.substring(m);
                        m = frest.indexOf(", ");
                        if ((m + 2) < frest.length()) {
                            String yr = frest.substring(m + 2).trim();
                            try {
                                Integer.parseInt(yr);
                                h.put(StandardField.YEAR, yr);
                            } catch (NumberFormatException ex) {
                                // Let's assume that this wasn't a number, since it
                                // couldn't be parsed as an integer.
                            }
                        }
                    }
                } else if ("AF".equals(f3)) {
                    h.put(StandardField.SCHOOL, frest.trim());
                } else if ("DT".equals(f3)) {
                    frest = frest.trim();
                    if ("Monograph".equals(frest)) {
                        type = StandardEntryType.BOOK;
                    } else if (frest.startsWith("Dissertation")) {
                        type = StandardEntryType.PHD_THESIS;
                    } else if (frest.toLowerCase(Locale.ROOT).contains(StandardField.JOURNAL.getName())) {
                        type = StandardEntryType.ARTICLE;
                    } else if ("Contribution".equals(frest) || "Chapter".equals(frest)) {
                        type = StandardEntryType.IN_COLLECTION;
                        // This entry type contains page numbers and booktitle in the
                        // title field.
                        isChapter = true;
                    } else {
                        type = EntryTypeFactory.parse(frest.replace(" ", ""));
                    }
                }
            }

            if (isChapter) {
                String titleO = h.get(StandardField.TITLE);
                if (titleO != null) {
                    String title = titleO.trim();
                    int inPos = title.indexOf("\" in ");
                    if (inPos > 1) {
                        h.put(StandardField.TITLE, title.substring(0, inPos));
                    }
                }
            }

            BibEntry b = new BibEntry(type);
            // create one here
            b.setField(h);

            bibitems.add(b);
        }

        return new ParserResult(bibitems);
    }
}
