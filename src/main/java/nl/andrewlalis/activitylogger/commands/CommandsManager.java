package nl.andrewlalis.activitylogger.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * A class which keeps track of a list of commands, in a registry where each command has a name.
 */
public class CommandsManager {

    /**
     * The registry of commands.
     */
    private Map<String, Command> commands;

    /**
     * Constructs a new CommandsManager, initialized with an empty command registry.
     */
    public CommandsManager() {
        this.commands = new HashMap<>();
    }

    /**
     * Adds a command to the manager's registry with the given keyword.
     *
     * @param keyword The word used to identify the command.
     * @param command The command to be invoked when the keyword is used.
     */
    public void registerCommand(String keyword, Command command) {
        this.commands.put(keyword, command);
    }

    /**
     * Gets the command that is identified by the given keyword.
     *
     * @param keyword The keyword for which to retrieve a command.
     * @return The command associated with the given keyword. Note that if the keyword does not refer to any existing
     * command, null will be returned.
     */
    public Command getCommand(String keyword) {
        return this.commands.get(keyword);
    }
}
