package nl.andrewlalis.activitylogger.view;

/**
 * Represents a user's intention to execute a command.
 */
public class CommandIntention {
	/**
	 * The command (in lowercase) that the user intends to execute.
	 */
	private String command;

	/**
	 * The list of arguments that the user wants to execute the command with.
	 */
	private String[] args;

	public CommandIntention(String command, String[] args) {
		this.command = command;
		this.args = args;
	}

	public String getCommand() {
		return command;
	}

	public String[] getArgs() {
		return args;
	}
}
