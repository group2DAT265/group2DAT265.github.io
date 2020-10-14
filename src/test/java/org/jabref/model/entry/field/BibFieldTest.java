package org.jabref.model.entry.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BibFieldTest {

    @Test
    void bibFieldsConsideredEqualIfUnderlyingFieldIsEqual() {
        assertEquals(new BibField(StandardField.AUTHOR, FieldPriority.IMPORTANT), new BibField(StandardField.AUTHOR, FieldPriority.DETAIL));
    }

    @Test
    void bibFieldsConsideredNotEqualIfUnderlyingFieldNotEqual() {
        assertNotEquals(new BibField(StandardField.AUTHOR, FieldPriority.IMPORTANT), new BibField(StandardField.TITLE, FieldPriority.IMPORTANT));
    }

    @Test
    void equalsReturnTrueWhenComapringTheSameBibField() {
        BibField bibField = new BibField(StandardField.AUTHOR, FieldPriority.IMPORTANT);
        assertTrue(bibField.equals(bibField));
    }

    @Test
    void equalsReturnFalseWhenComparingdifferentTypes() {
        BibField bibField = new BibField(StandardField.AUTHOR, FieldPriority.IMPORTANT);
        assertFalse(bibField.equals(1));
    }


}
