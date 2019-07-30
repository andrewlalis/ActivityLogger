package nl.andrewlalis.activitylogger;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunningActivityLogger {

    private String user;
    private BufferedReader reader;

    private boolean running = true;

    /**
     * Initializes the running activity logger and asks the user for their name.
     * @throws IOException If the program cannot read from standard input.
     */
    public RunningActivityLogger() throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter a username: ");
        this.user = this.reader.readLine();
    }

    /**
     * Starts the continuous loop of checking for input commands, and processing them.
     * @throws IOException If the program cannot read from standard input.
     */
    public void start() throws IOException {
        while (this.running) {
            System.out.println("Enter a command: ");
            String input = this.reader.readLine();
            String[] words = input.split(" ");
            if (words.length > 0) {
                String firstWord = words[0];
                if (firstWord.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting.");
                    this.running = false;
                } else if (firstWord.equalsIgnoreCase("log")) {
                    if (words.length > 1) {
                        String secondWord = words[1];
                        DatabaseManager manager = new DatabaseManager();
                        manager.insertEntry(new Entry(EntryType.valueOf(secondWord.toUpperCase()), this.user));
                    }
                }
            } else {
                System.out.println("Please enter commands as space-separated words.");
            }
        }
    }
}
