package nl.andrewlalis.activitylogger;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @throws IOException If something goes wrong when trying to read or write to/from the database.
     */
    public static void main(String[] args) throws ParseException, IOException {
        logger.log(Level.INFO, "Starting ActivityLogger...");
        Logger.getGlobal().setLevel(Level.ALL);

        CommandLine cmd = new DefaultParser().parse(getOptions(), args);

        // Check first if the 'log' option is provided, in which case the user wants to immediately add a log entry from the command-line.
        if (cmd.hasOption("l")) {
            if (!cmd.hasOption("t")) {
                logger.log(Level.WARNING, "No entry type has been provided.");
            } else if (!cmd.hasOption("u")) {
                logger.log(Level.WARNING, "No user has been provided.");
            } else {
                // Log a new entry.
                EntryType entryType = EntryType.valueOf(cmd.getOptionValue("t", EntryType.START.name()).toUpperCase());
                String user = cmd.getOptionValue("u", null);

                if (user == null) {
                    logger.log(Level.WARNING, "The given user is null.");
                } else {
                    DatabaseManager manager = new DatabaseManager();
                    manager.insertEntry(new Entry(entryType, user));
                    manager.close();
                }
            }
        } else {
            // Since no 'log' option is provided, assume that the user wants the interactive application.
            logger.log(Level.FINE, "Starting the running activity logger.");
            if (cmd.hasOption("nogui")) {
                new RunningActivityLogger().start();
            }
        }
    }

    /**
     * Builds a list of all possible command-line options for this application.
     * @return An options object containing all of the command-line options.
     */
    private static Options getOptions() {
        Options options = new Options();
        options.addOption("l", "log", false, "If this flag is set, a new entry will be logged, if not, the program will enter interactive mode.");
        options.addOption("t", "entrytype", true, "The type of entry.");
        options.addOption("u", "user", true, "The user to log an entry for.");
        options.addOption("nogui", false, "Set this flag to start the program in command-line mode, instead of with a user interface.");
        return options;
    }

}
