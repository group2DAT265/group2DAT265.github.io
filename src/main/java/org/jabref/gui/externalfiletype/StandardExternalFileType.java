package org.jabref.gui.externalfiletype;

import org.jabref.gui.icon.IconTheme;
import org.jabref.gui.icon.JabRefIcon;
import org.jabref.logic.l10n.Localization;

public enum StandardExternalFileType implements ExternalFileType {


    PDF("PDF", "pdf", "application/pdf", Constants.EVINCE, "pdfSmall", IconTheme.JabRefIcons.PDF_FILE),
    POSTSCRIPT("PostScript", "ps", "application/postscript", Constants.EVINCE, "psSmall", IconTheme.JabRefIcons.FILE),
    WORD("Word", "doc", "application/msword", Constants.OOWRITER, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_WORD),
    WORD_NEW("Word 2007+", "docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", Constants.OOWRITER, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_WORD),
    OPENDOCUMENT_TEXT(Localization.lang("OpenDocument text"), "odt", "application/vnd.oasis.opendocument.text", Constants.OOWRITER, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_OPENOFFICE),
    EXCEL("Excel", "xls", "application/excel", Constants.OOCALC, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_EXCEL),
    EXCEL_NEW("Excel 2007+", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", Constants.OOCALC, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_EXCEL),
    OPENDOCUMENT_SPREADSHEET(Localization.lang("OpenDocument spreadsheet"), "ods", "application/vnd.oasis.opendocument.spreadsheet", Constants.OOCALC, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_OPENOFFICE),
    POWERPOINT("PowerPoint", "ppt", "application/vnd.ms-powerpoint", Constants.OOIMPRESSS, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_POWERPOINT),
    POWERPOINT_NEW("PowerPoint 2007+", "pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation", Constants.OOIMPRESSS, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_POWERPOINT),
    OPENDOCUMENT_PRESENTATION(Localization.lang("OpenDocument presentation"), "odp", "application/vnd.oasis.opendocument.presentation", Constants.OOIMPRESSS, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_OPENOFFICE),
    RTF("Rich Text Format", "rtf", "application/rtf", Constants.OOWRITER, Constants.OPENOFFICE, IconTheme.JabRefIcons.FILE_TEXT),
    PNG(Localization.lang(Constants.IMAGE_0, "PNG"), "png", "image/png", "gimp", Constants.PICTURE, IconTheme.JabRefIcons.PICTURE),
    GIF(Localization.lang(Constants.IMAGE_0, "GIF"), "gif", "image/gif", "gimp", Constants.PICTURE, IconTheme.JabRefIcons.PICTURE),
    JPG(Localization.lang(Constants.IMAGE_0, "JPG"), "jpg", "image/jpeg", "gimp", Constants.PICTURE, IconTheme.JabRefIcons.PICTURE),
    DJVU("Djvu", "djvu", "image/vnd.djvu", Constants.EVINCE, "psSmall", IconTheme.JabRefIcons.FILE),
    TXT("Text", "txt", "text/plain", Constants.EMACS, Constants.EMACS, IconTheme.JabRefIcons.FILE_TEXT),
    TEX("LaTeX", "tex", "application/x-latex", Constants.EMACS, Constants.EMACS, IconTheme.JabRefIcons.FILE_TEXT),
    CHM("CHM", "chm", "application/mshelp", "gnochm", "www", IconTheme.JabRefIcons.WWW),
    TIFF(Localization.lang(Constants.IMAGE_0, "TIFF"), "tiff", "image/tiff", "gimp", Constants.PICTURE, IconTheme.JabRefIcons.PICTURE),
    URL("URL", "html", "text/html", Constants.FIREFOX, "www", IconTheme.JabRefIcons.WWW),
    MHT("MHT", "mht", "multipart/related", Constants.FIREFOX, "www", IconTheme.JabRefIcons.WWW),
    EPUB("ePUB", "epub", "application/epub+zip", Constants.FIREFOX, "www", IconTheme.JabRefIcons.WWW);

    private final String name;
    private final String extension;
    private final String mimeType;
    private final String openWith;
    private final String iconName;
    private final JabRefIcon icon;

    StandardExternalFileType(String name, String extension, String mimeType,
                             String openWith, String iconName, JabRefIcon icon) {
        this.name = name;
        this.extension = extension;
        this.mimeType = mimeType;
        this.openWith = openWith;
        this.iconName = iconName;
        this.icon = icon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String getOpenWithApplication() {
        // On all OSes there is a generic application available to handle file opening, so use this one
        return "";
    }

    @Override
    public JabRefIcon getIcon() {
        return icon;
    }

    private static class Constants {

        private static final String EVINCE = "evince";
        private static final String OPENOFFICE = "openoffice";
        private static final String OOWRITER = "oowriter";
        private static final String OOCALC = "oocalc";
        private static final String OOIMPRESSS = "ooimpress";
        private static final String PICTURE = "picture";
        private static final String IMAGE_0 = "%0 image";
        private static final String EMACS = "emacs";
        private static final String FIREFOX = "firefox";
    }
}
