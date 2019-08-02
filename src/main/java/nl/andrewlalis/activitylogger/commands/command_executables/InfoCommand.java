package nl.andrewlalis.activitylogger.commands.command_executables;

import nl.andrewlalis.activitylogger.database.DatabaseManager;
import nl.andrewlalis.activitylogger.model.EntryAnalytics;
import nl.andrewlalis.activitylogger.model.EntryType;

import java.time.Duration;

public class InfoCommand extends DatabaseCommand {

    public InfoCommand(DatabaseManager databaseManager, String user) {
        super(databaseManager, user);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            this.showBasicInfo();
        }
    }

    private void showBasicInfo() {
        Duration durationSinceLastStart = EntryAnalytics.getTotalEffectiveDuration(this.databaseManager.selectEntriesSinceMostRecentEntry(EntryType.START, this.user));

        System.out.println("Basic Info:");
        System.out.println(durationSinceLastStart.toString());
    }
}
