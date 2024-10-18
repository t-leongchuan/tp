package seedu.address.model.log;

import javafx.collections.ObservableList;
import seedu.address.model.log.Log;

/**
 * Unmodifiable view of a Log List
 */
public interface ReadOnlyLogList {

    /**
     * Returns an unmodifiable view of the log list.
     * This list will not contain any duplicate logs.
     */
    ObservableList<Log> getLogList();
}
