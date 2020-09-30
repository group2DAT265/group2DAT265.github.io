package org.jabref.logic.util.strings;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlCharsMapTest {

    private XmlCharsMap actual;

    @BeforeEach
    public void setUp() {
        actual = new XmlCharsMap();
    }

    @Test
    public void isHashMapStillCorrectAfterDuplicationRemoval() {
        final HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("\\{\\\\\\\"\\{a\\}\\}", "&#x00E4;");
        expected.put("\\{\\\\\\\"\\{A\\}\\}", "&#x00C4;");
        expected.put("\\{\\\\\\\"\\{e\\}\\}", "&#x00EB;");
        expected.put("\\{\\\\\\\"\\{E\\}\\}", "&#x00CB;");
        expected.put("\\{\\\\\\\"\\{i\\}\\}", "&#x00EF;");
        expected.put("\\{\\\\\\\"\\{I\\}\\}", "&#x00CF;");
        expected.put("\\{\\\\\\\"\\{o\\}\\}", "&#x00F6;");
        expected.put("\\{\\\\\\\"\\{O\\}\\}", "&#x00D6;");
        expected.put("\\{\\\\\\\"\\{u\\}\\}", "&#x00FC;");
        expected.put("\\{\\\\\\\"\\{U\\}\\}", "&#x00DC;");

        // next 2 rows were missing...
        expected.put("\\{\\\\\\`\\{a\\}\\}", "&#x00E0;");
        expected.put("\\{\\\\\\`\\{A\\}\\}", "&#x00C0;");

        expected.put("\\{\\\\\\`\\{e\\}\\}", "&#x00E8;");
        expected.put("\\{\\\\\\`\\{E\\}\\}", "&#x00C8;");
        expected.put("\\{\\\\\\`\\{i\\}\\}", "&#x00EC;");
        expected.put("\\{\\\\\\`\\{I\\}\\}", "&#x00CC;");
        expected.put("\\{\\\\\\`\\{o\\}\\}", "&#x00F2;");
        expected.put("\\{\\\\\\`\\{O\\}\\}", "&#x00D2;");
		expected.put("\\{\\\\\\`\\{u\\}\\}", "&#x00F9;");
		expected.put("\\{\\\\\\`\\{U\\}\\}", "&#x00D9;");

        // corrected these 10 lines below...
		expected.put("\\{\\\\\\'\\{a\\}\\}", "&#x00E1;");
		expected.put("\\{\\\\\\'\\{A\\}\\}", "&#x00C1;");
        expected.put("\\{\\\\\\'\\{e\\}\\}", "&#x00E9;");
		expected.put("\\{\\\\\\'\\{E\\}\\}", "&#x00C9;");
		expected.put("\\{\\\\\\'\\{i\\}\\}", "&#x00ED;");
		expected.put("\\{\\\\\\'\\{I\\}\\}", "&#x00CD;");
		expected.put("\\{\\\\\\'\\{o\\}\\}", "&#x00F3;");
		expected.put("\\{\\\\\\'\\{O\\}\\}", "&#x00D3;");
		expected.put("\\{\\\\\\'\\{u\\}\\}", "&#x00FA;");
		expected.put("\\{\\\\\\'\\{U\\}\\}", "&#x00DA;");
        // added next four chars...
        expected.put("\\{\\\\\\'\\{c\\}\\}", "&#x0107;");
        expected.put("\\{\\\\\\'\\{C\\}\\}", "&#x0106;");
        expected.put("\\{\\\\c\\{c\\}\\}", "&#x00E7;");
        expected.put("\\{\\\\c\\{C\\}\\}", "&#x00C7;");

		expected.put("\\{\\\\\\\uFFFD\\{E\\}\\}", "&#x00C9;");
		expected.put("\\{\\\\\\\uFFFD\\{i\\}\\}", "&#x00ED;");
		expected.put("\\{\\\\\\\uFFFD\\{I\\}\\}", "&#x00CD;");
		expected.put("\\{\\\\\\\uFFFD\\{o\\}\\}", "&#x00F3;");
		expected.put("\\{\\\\\\\uFFFD\\{O\\}\\}", "&#x00D3;");
		expected.put("\\{\\\\\\\uFFFD\\{u\\}\\}", "&#x00FA;");
		expected.put("\\{\\\\\\\uFFFD\\{U\\}\\}", "&#x00DA;");
		expected.put("\\{\\\\\\\uFFFD\\{a\\}\\}", "&#x00E1;");
		expected.put("\\{\\\\\\\uFFFD\\{A\\}\\}", "&#x00C1;");

        // next 2 rows were missing...
        expected.put("\\{\\\\\\^\\{a\\}\\}", "&#x00E2;");
        expected.put("\\{\\\\\\^\\{A\\}\\}", "&#x00C2;");

        expected.put("\\{\\\\\\^\\{o\\}\\}", "&#x00F4;");
        expected.put("\\{\\\\\\^\\{O\\}\\}", "&#x00D4;");
		expected.put("\\{\\\\\\^\\{u\\}\\}", "&#x00F9;");
		expected.put("\\{\\\\\\^\\{U\\}\\}", "&#x00D9;");
        expected.put("\\{\\\\\\^\\{e\\}\\}", "&#x00EA;");
        expected.put("\\{\\\\\\^\\{E\\}\\}", "&#x00CA;");
        expected.put("\\{\\\\\\^\\{i\\}\\}", "&#x00EE;");
        expected.put("\\{\\\\\\^\\{I\\}\\}", "&#x00CE;");

        expected.put("\\{\\\\\\~\\{o\\}\\}", "&#x00F5;");
        expected.put("\\{\\\\\\~\\{O\\}\\}", "&#x00D5;");
        expected.put("\\{\\\\\\~\\{n\\}\\}", "&#x00F1;");
        expected.put("\\{\\\\\\~\\{N\\}\\}", "&#x00D1;");
        expected.put("\\{\\\\\\~\\{a\\}\\}", "&#x00E3;");
        expected.put("\\{\\\\\\~\\{A\\}\\}", "&#x00C3;");

        expected.put("\\{\\\\\\\"a\\}", "&#x00E4;");
        expected.put("\\{\\\\\\\"A\\}", "&#x00C4;");
        expected.put("\\{\\\\\\\"e\\}", "&#x00EB;");
        expected.put("\\{\\\\\\\"E\\}", "&#x00CB;");
        expected.put("\\{\\\\\\\"i\\}", "&#x00EF;");
        expected.put("\\{\\\\\\\"I\\}", "&#x00CF;");
        expected.put("\\{\\\\\\\"o\\}", "&#x00F6;");
        expected.put("\\{\\\\\\\"O\\}", "&#x00D6;");
        expected.put("\\{\\\\\\\"u\\}", "&#x00FC;");
        expected.put("\\{\\\\\\\"U\\}", "&#x00DC;");

        // next 2 rows were missing...
        expected.put("\\{\\\\\\`a\\}", "&#x00E0;");
        expected.put("\\{\\\\\\`A\\}", "&#x00C0;");

        expected.put("\\{\\\\\\`e\\}", "&#x00E8;");
        expected.put("\\{\\\\\\`E\\}", "&#x00C8;");
        expected.put("\\{\\\\\\`i\\}", "&#x00EC;");
        expected.put("\\{\\\\\\`I\\}", "&#x00CC;");
        expected.put("\\{\\\\\\`o\\}", "&#x00F2;");
        expected.put("\\{\\\\\\`O\\}", "&#x00D2;");
		expected.put("\\{\\\\\\`u\\}", "&#x00F9;");
		expected.put("\\{\\\\\\`U\\}", "&#x00D9;");
        expected.put("\\{\\\\\\'e\\}", "&#x00E9;");
		expected.put("\\{\\\\\\'E\\}", "&#x00C9;");
		expected.put("\\{\\\\\\'i\\}", "&#x00ED;");
		expected.put("\\{\\\\\\'I\\}", "&#x00CD;");
		expected.put("\\{\\\\\\'o\\}", "&#x00F3;");
		expected.put("\\{\\\\\\'O\\}", "&#x00D3;");
		expected.put("\\{\\\\\\'u\\}", "&#x00FA;");
		expected.put("\\{\\\\\\'U\\}", "&#x00DA;");
		expected.put("\\{\\\\\\'a\\}", "&#x00E1;");
		expected.put("\\{\\\\\\'A\\}", "&#x00C1;");
        // added next two chars...
        expected.put("\\{\\\\\\'c\\}", "&#x0107;");
        expected.put("\\{\\\\\\'C\\}", "&#x0106;");

        // next two lines were wrong...
        expected.put("\\{\\\\\\^a\\}", "&#x00E2;");
        expected.put("\\{\\\\\\^A\\}", "&#x00C2;");

        expected.put("\\{\\\\\\^o\\}", "&#x00F4;");
        expected.put("\\{\\\\\\^O\\}", "&#x00D4;");
		expected.put("\\{\\\\\\^u\\}", "&#x00F9;");
		expected.put("\\{\\\\\\^U\\}", "&#x00D9;");
        expected.put("\\{\\\\\\^e\\}", "&#x00EA;");
        expected.put("\\{\\\\\\^E\\}", "&#x00CA;");
        expected.put("\\{\\\\\\^i\\}", "&#x00EE;");
        expected.put("\\{\\\\\\^I\\}", "&#x00CE;");
        expected.put("\\{\\\\\\~o\\}", "&#x00F5;");
        expected.put("\\{\\\\\\~O\\}", "&#x00D5;");
        expected.put("\\{\\\\\\~n\\}", "&#x00F1;");
        expected.put("\\{\\\\\\~N\\}", "&#x00D1;");
        expected.put("\\{\\\\\\~a\\}", "&#x00E3;");
        expected.put("\\{\\\\\\~A\\}", "&#x00C3;");
        assertEquals(expected, actual);

        
    }
    
}
