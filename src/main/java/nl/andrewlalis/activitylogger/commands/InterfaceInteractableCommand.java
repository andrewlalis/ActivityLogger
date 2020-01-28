package nl.andrewlalis.activitylogger.commands;

import nl.andrewlalis.activitylogger.view.UserInteractable;

/**
 * Commands which implement this one are given access to an interactable interface when executed.
 */
public interface InterfaceInteractableCommand extends Command {
	/**
	 * Executes this command.
	 * @param args The arguments to pass to it.
	 * @param interactable The interactable interface in which this command may communicate with the user.
	 */
	void execute(String[] args, UserInteractable interactable);
}
