package model;

/**
 * This class holds the different types of entries that can be saved.
 */
public enum EntryType {
    UNKNOWN(-1),
    START(0),
    STOP(1),
    PAUSE(2),
    UNPAUSE(3);

    private int value;

    /**
     * Constructs an EntryType with the given value.
     * @param value The integer value to assign to the entry type.
     */
    EntryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Gets the entry type for a particular integer value.
     * @param value The integer value that should refer to one of the defined entry types.
     * @return If the given value refers to a valid entry type, then that type will be returned, otherwise null.
     */
    public static EntryType fromValue(int value) {
        for (EntryType type : EntryType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
