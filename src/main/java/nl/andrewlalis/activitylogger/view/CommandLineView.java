package nl.andrewlalis.activitylogger.view;

import java.io.*;

public class CommandLineView implements UserInteractable {

    private BufferedReader reader;
    private BufferedWriter writer;

    public CommandLineView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public String promptForInput(String promptText) {
        try {
            this.writer.write(promptText);
            this.writer.newLine();
            return this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void showMessage(String message) {
        try {
            this.writer.write(message);
            this.writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        try {
            this.writer.write("Error: " + errorMessage);
            this.writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
