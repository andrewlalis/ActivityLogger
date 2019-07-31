package nl.andrewlalis.activitylogger.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

import static nl.andrewlalis.activitylogger.model.EntryType.*;

/**
 * This class provides some analytics for an entry or list of entries.
 */
public class EntryAnalytics {

    /**
     * Determines the total effective duration of a list of entries. That is, the effective duration is any time between
     * a start entry and a pause or stop entry, or if a pause has already occurred, the following unpause may be used.
     * @param entries The list of entries to compute the duration for.
     * @return A duration object.
     */
    public static Duration getTotalEffectiveDuration(List<Entry> entries) {
        Stack<Entry> entryStack = new Stack<>();

        for (int i = entries.size() - 1; i >= 0; i--) {
            entryStack.push(entries.get(i));
        }

        // Pop entries until we get to the first start, since this is where the magic happens.
        Entry startingEntry = null;
        while (startingEntry == null || startingEntry.getEntryType() != START) {
            if (entryStack.empty()) {
                return Duration.ZERO; // No starting point could be found, so just return the default.
            }
            startingEntry = entryStack.pop();
        }

        LocalDateTime start = startingEntry.getOccurredAt();
        //System.out.println("Start entry: " + start.toString());
        Duration duration = Duration.ZERO;
        boolean isStopped = false; // Keep track of whether or not we're in a section where duration should be counted (i.e. between start and stop).
        boolean isPaused = false;

        while (!entryStack.empty()) {
            Entry nextEntry = entryStack.pop();
            //System.out.println("Next entry: " + nextEntry.toString());
            if (isStopped) {
                //System.out.println("\tCurrently stopped");
                // The last entry was a STOP, so only look for a START. Anything else between is ignored.
                if (nextEntry.getEntryType() == START) {
                    isStopped = false;
                    start = nextEntry.getOccurredAt();
                }
            } else {
                //System.out.println("\tCurrently not stopped");
                // The clock is not stopped, so check first if it is paused.
                if (isPaused) {
                    //System.out.println("\t\tCurrently paused");
                    // Ignore everything after a pause except for unpause and stop.
                    if (nextEntry.getEntryType() == UNPAUSE) {
                        isPaused = false;
                        start = nextEntry.getOccurredAt();
                    } else if (nextEntry.getEntryType() == STOP) {
                        isPaused = false;
                        isStopped = true;
                    }
                } else {
                    //System.out.println("\t\tCurrently not paused");
                    // Not paused, and not stopped, so check to see if that needs to happen.
                    if (nextEntry.getEntryType() == STOP) {
                        isStopped = true;
                        LocalDateTime stop = nextEntry.getOccurredAt();
                        duration = duration.plus(Duration.between(start, stop));
                    } else if (nextEntry.getEntryType() == PAUSE) {
                        isPaused = true;
                        LocalDateTime stop = nextEntry.getOccurredAt();
                        duration = duration.plus(Duration.between(start, stop));
                    }
                }
            }
        }

        // If the end is reached and the clock is still running, add the duration up to the current time.
        if (!isStopped && !isPaused) {
            duration = duration.plus(Duration.between(start, LocalDateTime.now()));
        }

        return duration;
    }
}
