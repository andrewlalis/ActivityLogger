package nl.andrewlalis.activitylogger.commands.command_executables;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;

/**
 * Logs a simple entry to the database for a particular user.
 */
public class LogEntryCommand extends DatabaseCommand {

    /**
     * The entry type of any entry added with this command.
     */
    private EntryType entryType;

    public LogEntryCommand(DatabaseManager databaseManager, String user, EntryType entryType) {
        super(databaseManager, user);
        this.entryType = entryType;
    }

    /**
     * Executes this command, and enters a new entry of the pre-defined type.
     *
     * @param args The list of arguments the user has given to this command. In this case, it's not needed at all.
     */
    @Override
    public void execute(String[] args) {
        this.databaseManager.insertEntry(new Entry(this.entryType, this.user));
    }
}
