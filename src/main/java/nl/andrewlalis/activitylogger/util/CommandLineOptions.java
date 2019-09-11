package nl.andrewlalis.activitylogger.util;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This class contains all the options that are available as command-line arguments.
 */
public class CommandLineOptions {

	public static final Option logOption = new Option(
			"l",
			"log",
			false,
			"If this flag is set, a new entry will be logged, if not, the program will enter interactive mode."
	);

	public static final Option entryTypeOption = new Option(
			"t",
			"entrytype",
			true,
			"The type of entry."
	);

	public static final Option userOption = new Option(
			"u",
			"user",
			true,
			"The user to log an entry for."
	);

	public static final Option noGuiOption = new Option(
			"nogui",
			false,
			"Set this flag to start the program in command-line mode, instead of with a user interface."
	);

	/**
	 * @return The options to use when parsing the command-line arguments.
	 */
	public static Options getOptions() {
		Options options = new Options();
		options.addOption(logOption);
		options.addOption(entryTypeOption);
		options.addOption(userOption);
		options.addOption(noGuiOption);
		return options;
	}
}
