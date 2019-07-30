package database;

import model.Entry;
import model.EntryType;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles the various tedious tasks associated with maintaining a database.
 */
public class DatabaseManager {

    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());

    private static final String URL = "jdbc:sqlite:my_db.sqlite3";

    /**
     * The connection to the database. This is maintained during the lifetime of this class.
     */
    private Connection connection;

    public DatabaseManager() {
        this.initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            this.connection = DriverManager.getConnection(URL);
            logger.log(Level.FINE, "Obtained a valid connection to the database.");

            Statement statement = this.connection.createStatement();
            statement.execute(SqlReader.readFromFile("schema.sql"));
            logger.log(Level.FINE, "Executed schema.sql.");
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "An error occurred while initializing the database: " + exception.getMessage());
        }
    }

    /**
     * Selects an entry from the database with the given id.
     * @param id The id to use to find an entry.
     * @return The entry with the given id, or null if none could be found.
     */
    public Entry selectEntry(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SqlReader.readFromFile("select_entry_by_id.sql"));
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            if (!results.next()) {
                return null;
            }

            EntryType entryType = EntryType.fromValue(results.getInt("entry_type"));
            String user = results.getString("user");
            String occuredAtString = results.getString("occurred_at");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime occurredAt = LocalDateTime.parse(occuredAtString, formatter);

            return new Entry(id, entryType, occurredAt, user);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while selecting an entry: " + e.getMessage());
            return null;
        }
    }

    /**
     * Inserts an entry into the database at the current time.
     * @param entry The entry to add.
     */
    public void insertEntry(Entry entry) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SqlReader.readFromFile("insert_entry.sql"));
            statement.setInt(1, entry.getEntryType().getValue());
            statement.setString(2, entry.getUser());
            statement.executeUpdate();
            ResultSet results = statement.getGeneratedKeys();
            results.next();
            int id = results.getInt(1);
            logger.log(Level.INFO, "Inserted a new entry with id = " + id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while inserting an entry: " + e.getMessage());
        }
    }

}
