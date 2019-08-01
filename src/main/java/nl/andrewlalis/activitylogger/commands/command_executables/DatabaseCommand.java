package nl.andrewlalis.activitylogger.commands.command_executables;

import nl.andrewlalis.activitylogger.commands.Command;
import nl.andrewlalis.activitylogger.database.DatabaseManager;

/**
 * Commands of this sort are provided with a database manager to handle any queries that are required.
 */
public abstract class DatabaseCommand implements Command {

    protected DatabaseManager databaseManager;
}
