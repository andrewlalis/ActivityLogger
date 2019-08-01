package nl.andrewlalis.activitylogger.commands.command_executables;

import nl.andrewlalis.activitylogger.RunningActivityLogger;
import nl.andrewlalis.activitylogger.commands.Command;

/**
 * This command is run when the user wishes to exit the program.
 */
public class ExitCommand implements Command {

    private RunningActivityLogger runningActivityLogger;

    public ExitCommand(RunningActivityLogger runningActivityLogger) {
        this.runningActivityLogger = runningActivityLogger;
    }

    @Override
    public void execute(String[] args) {
        this.runningActivityLogger.setRunning(false);
    }
}
