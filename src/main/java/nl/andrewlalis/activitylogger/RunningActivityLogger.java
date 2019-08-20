package nl.andrewlalis.activitylogger;

import nl.andrewlalis.activitylogger.commands.Command;
import nl.andrewlalis.activitylogger.commands.CommandsManager;
import nl.andrewlalis.activitylogger.commands.command_executables.ExitCommand;
import nl.andrewlalis.activitylogger.commands.command_executables.InfoCommand;
import nl.andrewlalis.activitylogger.commands.command_executables.LogEntryCommand;
import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.EntryType;
import nl.andrewlalis.activitylogger.view.CommandLineView;
import nl.andrewlalis.activitylogger.view.UserInteractable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * An interactive interface for the activity logger, that provides a command-line way to log data and retrieve data.
 */
public class RunningActivityLogger {

    private BufferedReader reader;

    private CommandsManager commandsManager;

    private boolean running = true;

    private UserInteractable interactableInterface;

    /**
     * Initializes the running activity logger and asks the user for their name.
     * @throws IOException If the program cannot read from standard input.
     */
    public RunningActivityLogger() throws IOException {
        this.interactableInterface = new CommandLineView();
        String user = this.interactableInterface.promptForInput("Enter a username: ");

        DatabaseManager manager = new DatabaseManager();

        this.commandsManager = new CommandsManager();

        this.commandsManager.registerCommand("exit", new ExitCommand(this));
        this.commandsManager.registerCommand("start", new LogEntryCommand(manager, user, EntryType.START));
        this.commandsManager.registerCommand("stop", new LogEntryCommand(manager, user, EntryType.STOP));
        this.commandsManager.registerCommand("pause", new LogEntryCommand(manager, user, EntryType.PAUSE));
        this.commandsManager.registerCommand("unpause", new LogEntryCommand(manager, user, EntryType.UNPAUSE));
        this.commandsManager.registerCommand("info", new InfoCommand(manager, user));
    }

    /**
     * Starts the continuous loop of checking for input commands, and processing them.
     *
     * The user will enter a word, or multiple words separated by spaces, and the first word will be considered as a
     * command keyword. Then, the commands manager tries to retrieve the user's intended command and execute it, passing
     * any additional words as arguments to the command.
     *
     * @throws IOException If the program cannot read from standard input.
     */
    public void start() throws IOException {

        while (this.running) {
            String input = this.interactableInterface.promptForInput("Enter a command: ");
            String[] words = input.split(" ");
            if (words.length > 0) {
                String firstWord = words[0];
                String[] args = Arrays.copyOfRange(words, 1, words.length);

                Command command = this.commandsManager.getCommand(firstWord.toLowerCase());
                if (command != null) {
                    command.execute(args);
                } else {
                    this.interactableInterface.showErrorMessage("Command not recognized. Please enter a valid command.");
                }
            } else {
                this.interactableInterface.showErrorMessage("Please enter commands as space-separated words.");
            }
        }
    }

    /**
     * Allow external control over when to quit this activity logger.
     * @param value Set this to false to quit the activity logger.
     */
    public void setRunning(boolean value) {
        this.running = value;
    }
}
