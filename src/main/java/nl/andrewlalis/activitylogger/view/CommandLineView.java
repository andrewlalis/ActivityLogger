package nl.andrewlalis.activitylogger.view;

import java.io.*;
import java.util.logging.Logger;

/**
 * Represents a type of interactable view in which the user simply uses the standard input and output streams.
 */
public class CommandLineView implements UserInteractable {

    private static final Logger logger = Logger.getLogger(CommandLineView.class.getName());

    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * Constructs a new Command Line View with a reader and writer attached to the standard streams.
     */
    public CommandLineView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public String promptForInput(String promptText) {
        try {
            this.writeLine(promptText);
            return this.reader.readLine();
        } catch (IOException e) {
            logger.warning("Could not read input.");
            return null;
        }
    }

    @Override
    public void showMessage(String message) {
        this.writeLine(message);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        this.writeLine("Error: " + errorMessage);
    }

    /**
     * Writes a line of output to the STDOUT stream, followed by a newline character.
     * @param s The string to output to the STDOUT.
     */
    private void writeLine(String s) {
        try {
            this.writer.write(s);
            this.writer.newLine();
            this.writer.flush();
        } catch (IOException e) {
            logger.warning("Could not write output string: " + s);
        }
    }
}
