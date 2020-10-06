package org.jabref.logic.integrity;

import java.util.Optional;

import org.jabref.logic.l10n.Localization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ValidCitationKeyCheckerTest {

    private final ValidCitationKeyChecker checker = new ValidCitationKeyChecker();

    @Test
    void citationKeyIsEmptyString() {
        // assertNotEquals(Optional.empty(), checker.checkValue(""));
        assertEquals(Optional.of(Localization.lang("empty citation key")), checker.checkValue(""));
    }

    @Test
    void citationKeyIsNull() {
        // assertNotEquals(Optional.empty(), checker.checkValue(null));
        assertEquals(Optional.of(Localization.lang("empty citation key")), checker.checkValue(null));
    }

    @Test
    void citationKeyIsValid() {
        // assertNotEquals(Optional.empty(), checker.checkValue(null));
        assertEquals(Optional.empty(), checker.checkValue("Slominski2019"));
    }

    @Test
    void citationKeyIsNotValid() {
        // assertNotEquals(Optional.empty(), checker.checkValue(null));
        assertEquals(Optional.of(Localization.lang("Invalid citation key")), checker.checkValue("Slominski2019ÖÖ%%"));
    }


}
