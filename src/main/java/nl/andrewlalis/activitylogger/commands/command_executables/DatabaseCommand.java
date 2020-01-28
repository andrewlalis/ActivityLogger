package nl.andrewlalis.activitylogger.commands.command_executables;

import nl.andrewlalis.activitylogger.commands.Command;
import nl.andrewlalis.activitylogger.database.DatabaseManager;

/**
 * Commands of this sort are provided with a database manager to handle any queries that are required.
 */
public abstract class DatabaseCommand implements Command {

    protected DatabaseManager databaseManager;
    protected String user;

    /**
     * Constructs a new database command.
     *
     * @param databaseManager The manager that will be provided to the command when it's executed.
     * @param user The user executing the command.
     */
    public DatabaseCommand(DatabaseManager databaseManager, String user) {
        this.databaseManager = databaseManager;
        this.user = user;
    }
}
