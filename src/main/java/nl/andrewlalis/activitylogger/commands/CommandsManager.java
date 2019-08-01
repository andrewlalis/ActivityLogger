package nl.andrewlalis.activitylogger.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandsManager {

    private Map<String, Command> commands;

    public CommandsManager() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(String keyword, Command command) {
        this.commands.put(keyword, command);
    }

    public Command getCommand(String keyword) {
        return this.commands.get(keyword);
    }
}
