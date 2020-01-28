package nl.andrewlalis.activitylogger.commands;

/**
 * Classes which implement this interface represent commands that can be invoked by the user by typing certain keywords.
 */
public interface Command {
    /**
     * Executes this command, with the given arguments.
     * @param args The list of arguments the user has given to this command.
     */
    void execute(String[] args);
}
