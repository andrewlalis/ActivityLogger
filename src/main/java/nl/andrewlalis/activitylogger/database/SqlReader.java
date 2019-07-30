package nl.andrewlalis.activitylogger.database;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * This class manages the task of reading various snippets of SQL from the project's resources.
 */
public class SqlReader {

    public static String readFromFile(String filename) {
        try {
            return Files.lines(Paths.get(SqlReader.class.getClassLoader().getResource("sql/" + filename).toURI()))
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }

    }
}
