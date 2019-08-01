package nl.andrewlalis.activitylogger.commands.command_executables;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;

/**
 * When the user executes this command, they indicate that they wish to log a new START entry.
 */
public class StartCommand extends DatabaseCommand {

    private String user;

    public StartCommand(DatabaseManager databaseManager, String user) {
        this.databaseManager = databaseManager;
        this.user = user;
    }

    @Override
    public void execute(String[] args) {
        this.databaseManager.insertEntry(new Entry(EntryType.START, this.user));
    }
}
