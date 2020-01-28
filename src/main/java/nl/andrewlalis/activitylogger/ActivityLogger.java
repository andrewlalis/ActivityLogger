package nl.andrewlalis.activitylogger;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.andrewlalis.activitylogger.util.CommandLineOptions.*;

/**
 * The main activity logger class which serves as the program entry point.
 */
public class ActivityLogger {

    private static final Logger logger = Logger.getLogger(ActivityLogger.class.getName());

    /**
     * The main method where the program starts.
     *
     * @param args Any arguments passed via the command-line.
     * @throws ParseException If it is not possible to parse the options from the command line.
     */
    public static void main(String[] args) throws ParseException {
        logger.log(Level.INFO, "Starting ActivityLogger...");
        Logger.getGlobal().setLevel(Level.ALL);

        CommandLine cmd = new DefaultParser().parse(getOptions(), args);

        // Check first if the 'log' option is provided, in which case the user wants to immediately add a log entry from the command-line.
        if (cmd.hasOption(logOption.getOpt())) {
            if (!cmd.hasOption(entryTypeOption.getOpt())) {
                logger.log(Level.WARNING, "No entry type has been provided.");
            } else if (!cmd.hasOption("u")) {
                logger.log(Level.WARNING, "No user has been provided.");
            } else {
                logSingleEntry(cmd);
            }
        } else {
            // Since no 'log' option is provided, assume that the user wants the interactive application.
            logger.log(Level.FINE, "Starting the running activity logger.");
            if (cmd.hasOption(noGuiOption.getOpt())) {
                new RunningActivityLogger().start();
            } else {
                // TODO: Start a swing activity logger thing.
            }
        }
    }

    /**
     * Handles the case where a user has invoked the program with the purpose of logging a single entry.
     *
     * @param cmd A reference to the command line object.
     */
    private static void logSingleEntry(CommandLine cmd) {
        // Get the entry type the user has given, or default to a START type.
        EntryType entryType = EntryType.valueOf(cmd.getOptionValue(entryTypeOption.getOpt(), EntryType.START.name()).toUpperCase());
        // Get the user.
        String user = cmd.getOptionValue(userOption.getOpt(), null);
        if (user == null) {
            logger.log(Level.WARNING, "The given user is null.");
            return;
        }

        DatabaseManager manager = new DatabaseManager();
        manager.insertEntry(new Entry(entryType, user));
        manager.close();
    }
}
