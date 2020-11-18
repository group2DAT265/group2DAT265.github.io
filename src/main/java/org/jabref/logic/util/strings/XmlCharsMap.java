package org.jabref.logic.util.strings;

import java.util.HashMap;

public class XmlCharsMap extends HashMap<String, String> {

    private static final String F9 = "&#x00F9;";
    private static final String D9 = "&#x00D9;";
    private static final String E1 = "&#x00E1;";
    private static final String C1 = "&#x00C1;";
    private static final String C9 = "&#x00C9;";
    private static final String ED = "&#x00ED;";
    private static final String CD = "&#x00CD;";
    private static final String F3 = "&#x00F3;";
    private static final String D3 = "&#x00D3;";
    private static final String FA = "&#x00FA;";
    private static final String DA = "&#x00DA;";

    public XmlCharsMap() {
        put("\\{\\\\\\\"\\{a\\}\\}", "&#x00E4;");
        put("\\{\\\\\\\"\\{A\\}\\}", "&#x00C4;");
        put("\\{\\\\\\\"\\{e\\}\\}", "&#x00EB;");
        put("\\{\\\\\\\"\\{E\\}\\}", "&#x00CB;");
        put("\\{\\\\\\\"\\{i\\}\\}", "&#x00EF;");
        put("\\{\\\\\\\"\\{I\\}\\}", "&#x00CF;");
        put("\\{\\\\\\\"\\{o\\}\\}", "&#x00F6;");
        put("\\{\\\\\\\"\\{O\\}\\}", "&#x00D6;");
        put("\\{\\\\\\\"\\{u\\}\\}", "&#x00FC;");
        put("\\{\\\\\\\"\\{U\\}\\}", "&#x00DC;");
        // next 2 rows were missing...
        put("\\{\\\\\\`\\{a\\}\\}", "&#x00E0;");
        put("\\{\\\\\\`\\{A\\}\\}", "&#x00C0;");
        put("\\{\\\\\\`\\{e\\}\\}", "&#x00E8;");
        put("\\{\\\\\\`\\{E\\}\\}", "&#x00C8;");
        put("\\{\\\\\\`\\{i\\}\\}", "&#x00EC;");
        put("\\{\\\\\\`\\{I\\}\\}", "&#x00CC;");
        put("\\{\\\\\\`\\{o\\}\\}", "&#x00F2;");
        put("\\{\\\\\\`\\{O\\}\\}", "&#x00D2;");
        put("\\{\\\\\\`\\{u\\}\\}", F9);
        put("\\{\\\\\\`\\{U\\}\\}", D9);
        // corrected these 10 lines below...
        put("\\{\\\\\\'\\{a\\}\\}", E1);
        put("\\{\\\\\\'\\{A\\}\\}", C1);
        put("\\{\\\\\\'\\{e\\}\\}", "&#x00E9;");
        put("\\{\\\\\\'\\{E\\}\\}", C9);
        put("\\{\\\\\\'\\{i\\}\\}", ED);
        put("\\{\\\\\\'\\{I\\}\\}", CD);
        put("\\{\\\\\\'\\{o\\}\\}", F3);
        put("\\{\\\\\\'\\{O\\}\\}", D3);
        put("\\{\\\\\\'\\{u\\}\\}", FA);
        put("\\{\\\\\\'\\{U\\}\\}", DA);
        // added next four chars...
        put("\\{\\\\\\'\\{c\\}\\}", "&#x0107;");
        put("\\{\\\\\\'\\{C\\}\\}", "&#x0106;");
        put("\\{\\\\c\\{c\\}\\}", "&#x00E7;");
        put("\\{\\\\c\\{C\\}\\}", "&#x00C7;");
        put("\\{\\\\\\\uFFFD\\{E\\}\\}", C9);
        put("\\{\\\\\\\uFFFD\\{i\\}\\}", ED);
        put("\\{\\\\\\\uFFFD\\{I\\}\\}", CD);
        put("\\{\\\\\\\uFFFD\\{o\\}\\}", F3);
        put("\\{\\\\\\\uFFFD\\{O\\}\\}", D3);
        put("\\{\\\\\\\uFFFD\\{u\\}\\}", FA);
        put("\\{\\\\\\\uFFFD\\{U\\}\\}", DA);
        put("\\{\\\\\\\uFFFD\\{a\\}\\}", E1);
        put("\\{\\\\\\\uFFFD\\{A\\}\\}", C1);
        // next 2 rows were missing...
        put("\\{\\\\\\^\\{a\\}\\}", "&#x00E2;");
        put("\\{\\\\\\^\\{A\\}\\}", "&#x00C2;");
        put("\\{\\\\\\^\\{o\\}\\}", "&#x00F4;");
        put("\\{\\\\\\^\\{O\\}\\}", "&#x00D4;");
        put("\\{\\\\\\^\\{u\\}\\}", F9);
        put("\\{\\\\\\^\\{U\\}\\}", D9);
        put("\\{\\\\\\^\\{e\\}\\}", "&#x00EA;");
        put("\\{\\\\\\^\\{E\\}\\}", "&#x00CA;");
        put("\\{\\\\\\^\\{i\\}\\}", "&#x00EE;");
        put("\\{\\\\\\^\\{I\\}\\}", "&#x00CE;");
        put("\\{\\\\\\~\\{o\\}\\}", "&#x00F5;");
        put("\\{\\\\\\~\\{O\\}\\}", "&#x00D5;");
        put("\\{\\\\\\~\\{n\\}\\}", "&#x00F1;");
        put("\\{\\\\\\~\\{N\\}\\}", "&#x00D1;");
        put("\\{\\\\\\~\\{a\\}\\}", "&#x00E3;");
        put("\\{\\\\\\~\\{A\\}\\}", "&#x00C3;");
        put("\\{\\\\\\\"a\\}", "&#x00E4;");
        put("\\{\\\\\\\"A\\}", "&#x00C4;");
        put("\\{\\\\\\\"e\\}", "&#x00EB;");
        put("\\{\\\\\\\"E\\}", "&#x00CB;");
        put("\\{\\\\\\\"i\\}", "&#x00EF;");
        put("\\{\\\\\\\"I\\}", "&#x00CF;");
        put("\\{\\\\\\\"o\\}", "&#x00F6;");
        put("\\{\\\\\\\"O\\}", "&#x00D6;");
        put("\\{\\\\\\\"u\\}", "&#x00FC;");
        put("\\{\\\\\\\"U\\}", "&#x00DC;");
        // next 2 rows were missing...
        put("\\{\\\\\\`a\\}", "&#x00E0;");
        put("\\{\\\\\\`A\\}", "&#x00C0;");
        put("\\{\\\\\\`e\\}", "&#x00E8;");
        put("\\{\\\\\\`E\\}", "&#x00C8;");
        put("\\{\\\\\\`i\\}", "&#x00EC;");
        put("\\{\\\\\\`I\\}", "&#x00CC;");
        put("\\{\\\\\\`o\\}", "&#x00F2;");
        put("\\{\\\\\\`O\\}", "&#x00D2;");
        put("\\{\\\\\\`u\\}", F9);
        put("\\{\\\\\\`U\\}", D9);
        put("\\{\\\\\\'e\\}", "&#x00E9;");
        put("\\{\\\\\\'E\\}", C9);
        put("\\{\\\\\\'i\\}", ED);
        put("\\{\\\\\\'I\\}", CD);
        put("\\{\\\\\\'o\\}", F3);
        put("\\{\\\\\\'O\\}", D3);
        put("\\{\\\\\\'u\\}", FA);
        put("\\{\\\\\\'U\\}", DA);
        put("\\{\\\\\\'a\\}", E1);
        put("\\{\\\\\\'A\\}", C1);
        // added next two chars...
        put("\\{\\\\\\'c\\}", "&#x0107;");
        put("\\{\\\\\\'C\\}", "&#x0106;");
        // next two lines were wrong...
        put("\\{\\\\\\^a\\}", "&#x00E2;");
        put("\\{\\\\\\^A\\}", "&#x00C2;");
        put("\\{\\\\\\^o\\}", "&#x00F4;");
        put("\\{\\\\\\^O\\}", "&#x00D4;");
        put("\\{\\\\\\^u\\}", F9);
        put("\\{\\\\\\^U\\}", D9);
        put("\\{\\\\\\^e\\}", "&#x00EA;");
        put("\\{\\\\\\^E\\}", "&#x00CA;");
        put("\\{\\\\\\^i\\}", "&#x00EE;");
        put("\\{\\\\\\^I\\}", "&#x00CE;");
        put("\\{\\\\\\~o\\}", "&#x00F5;");
        put("\\{\\\\\\~O\\}", "&#x00D5;");
        put("\\{\\\\\\~n\\}", "&#x00F1;");
        put("\\{\\\\\\~N\\}", "&#x00D1;");
        put("\\{\\\\\\~a\\}", "&#x00E3;");
        put("\\{\\\\\\~A\\}", "&#x00C3;");
    }
}
