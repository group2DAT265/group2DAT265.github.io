package org.jabref.logic.integrity;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ValidCitationKeyCheckerTest {

    private final ValidCitationKeyChecker checker = new ValidCitationKeyChecker();

    @Test
    void citationKeyIsEmptyString() {
        assertNotEquals(Optional.empty(), checker.checkValue(""));
    }

    @Test
    void citationKeyIsNull() {
        assertNotEquals(Optional.empty(), checker.checkValue(null));
    }
}
