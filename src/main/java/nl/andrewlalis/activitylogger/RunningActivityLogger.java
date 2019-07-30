package nl.andrewlalis.activitylogger;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.Entry;
import nl.andrewlalis.activitylogger.model.EntryType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
        DatabaseManager manager = new DatabaseManager();

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
                        manager.insertEntry(new Entry(EntryType.valueOf(secondWord.toUpperCase()), this.user));
                    }
                } else if (firstWord.equalsIgnoreCase("info")) {
                    if (words.length > 1) {
                        String secondWord = words[1];
                        if (secondWord.equalsIgnoreCase("today")) {
                            System.out.println("Information about today's activity logs:");

                            Entry mostRecentStart = manager.selectMostRecentEntry(EntryType.START, this.user);
                            LocalDateTime currentTime = LocalDateTime.now();
                            Duration duration = Duration.between(mostRecentStart.getOccurredAt(), currentTime);

                            System.out.println("\tMost recent start: " + mostRecentStart.getOccurredAt());
                            System.out.println("\tDuration: " + duration.toString());

                            List<Entry> allEntries = manager.selectEntriesSinceMostRecentEntry(EntryType.START, this.user);
                            System.out.println("\tEntries since the most recent start: ");
                            for (Entry entry : allEntries) {
                                System.out.println("\t\t" + entry);
                            }
                        }
                    } else {
                        // Print generic info.
                        System.out.println(manager.selectEntry(3));
                    }
                }
            } else {
                System.out.println("Please enter commands as space-separated words.");
            }
        }

        manager.close();
    }
}
