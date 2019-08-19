package nl.andrewlalis.activitylogger.view;

/**
 * Represents a method of interacting with a user. Usually, this means either via command-line, or via a user interface.
 */
public interface UserInteractable {

    /**
     * When this method is called, the interactable object should show the given text to the user, and return the user's
     * input text.
     *
     * @param promptText A string of text that prompts the user to enter some value.
     * @return Whatever the user entered in response to the prompt text.
     */
    String promptForInput(String promptText);

    /**
     * Show a generic textual message to the user.
     *
     * @param message The message to show to the user.
     */
    void showMessage(String message);

    /**
     * Show an error message to the user.
     *
     * @param errorMessage The error message to show to the user.
     */
    void showErrorMessage(String errorMessage);
}
