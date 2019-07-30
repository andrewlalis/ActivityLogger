import database.DatabaseManager;
import model.Entry;
import model.EntryType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityLogger {

    private static final Logger logger = Logger.getLogger(ActivityLogger.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "Starting ActivityLogger...");

        DatabaseManager manager = new DatabaseManager();
        manager.insertEntry(new Entry(EntryType.START, "andrew"));

        Entry entry = manager.selectEntry(3);
        logger.log(Level.INFO, entry.toString());
    }

}
