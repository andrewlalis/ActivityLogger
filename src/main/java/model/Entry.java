package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents one activity entry in the database. This is usually either starting or stopping some activity.
 */
public class Entry {

    /**
     * The id of this entry.
     */
    private int id;

    /**
     * The type of this entry.
     */
    private EntryType entryType;

    /**
     * The date and time at which this entry took place.
     */
    private LocalDateTime occurredAt;

    /**
     * The user who is responsible for the creation of this entry.
     */
    private String user;

    public Entry(int id, EntryType entryType, LocalDateTime occurredAt, String user) {
        this.id = id;
        this.entryType = entryType;
        this.occurredAt = occurredAt;
        this.user = user;
    }

    public Entry() {
        this(-1, EntryType.UNKNOWN, LocalDateTime.now(), null);
    }

    public Entry(EntryType entryType, String user) {
        this(-1, entryType, LocalDateTime.now(), user);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %d, entry type: %s, occurred at: %s, user: %s",
                this.id,
                this.entryType.name(),
                this.occurredAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                this.user
        );
    }
}
