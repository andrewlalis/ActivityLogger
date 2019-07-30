import database.DatabaseManager;
import model.Entry;
import model.EntryType;
import org.apache.commons.cli.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityLogger {

    private static final Logger logger = Logger.getLogger(ActivityLogger.class.getName());

    public static void main(String[] args) throws ParseException {
        logger.log(Level.INFO, "Starting ActivityLogger...");
        DatabaseManager manager = new DatabaseManager();

        Options options = new Options();
        options.addOption("l", "log", false, "If this flag is set, a new entry will be logged.");
        options.addOption("t", "entrytype", true, "The type of entry.");
        options.addOption("u", "user", true, "The user to log an entry for.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("l")) {
            // Log a new entry.
            String entryType = cmd.getOptionValue("t", EntryType.START.name());
            String user = cmd.getOptionValue("u", null);
        }

        manager.insertEntry(new Entry(EntryType.START, "andrew"));

        Entry entry = manager.selectEntry(3);
        logger.log(Level.INFO, entry.toString());

        manager.close();
    }

}
