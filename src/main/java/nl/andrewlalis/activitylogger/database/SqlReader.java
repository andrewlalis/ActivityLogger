package nl.andrewlalis.activitylogger.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class manages the task of reading various snippets of SQL from the project's resources.
 */
public class SqlReader {

    public static final String DIRECTORY = "sql/";

    /**
     * Reads some lines from a file in the classpath, particularly inside the sql directory in resources.
     * @param filename The name of the file.
     * @return The contents of the file, or an empty string if nothing could be read.
     */
    public static String readFromFile(String filename) {
        InputStream inputStream = SqlReader.class.getClassLoader().getResourceAsStream(DIRECTORY + filename);
        if (inputStream == null) {
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
