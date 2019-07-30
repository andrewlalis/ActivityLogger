package nl.andrewlalis.activitylogger;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main activity logger class which serves as the program entry point.
 */
public class ActivityLogger {

    private static final Logger logger = Logger.getLogger(ActivityLogger.class.getName());

    public static void main(String[] args) throws ParseException {
        logger.log(Level.INFO, "Starting nl.andrewlalis.activitylogger.ActivityLogger...");

        DatabaseManager manager = new DatabaseManager();
        CommandLine cmd = new DefaultParser().parse(getOptions(), args);

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
                    manager.insertEntry(new Entry(entryType, user));
                }
            }
        } else {
            // Start running application.

        }

        manager.close();
    }

    /**
     * Builds a list of all possible command-line options for this application.
     * @return An options object containing all of the command-line options.
     */
    private static Options getOptions() {
        Options options = new Options();
        options.addOption("l", "log", false, "If this flag is set, a new entry will be logged.");
        options.addOption("t", "entrytype", true, "The type of entry.");
        options.addOption("u", "user", true, "The user to log an entry for.");
        return options;
    }

}
