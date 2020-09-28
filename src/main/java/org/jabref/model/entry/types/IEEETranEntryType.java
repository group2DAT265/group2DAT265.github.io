package org.jabref.model.entry.types;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum IEEETranEntryType implements EntryType {

    IEEE_TRAN_BSTCTL("IEEEtranBSTCTL"),
    ELECTRONIC("Electronic"),
    PATENT("Patent"),
    PERIODICAL("Periodical"),
    STANDARD("Standard");

    private final String displayName;

    IEEETranEntryType(String displayName) {
        this.displayName = displayName;
    }

    public static Optional<IEEETranEntryType> fromName(String name) {
        return Arrays.stream(IEEETranEntryType.values())
                     .filter(field -> field.getName().equalsIgnoreCase(name))
                     .findAny();
    }

    @Override
    public String getName() {
        return displayName.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
