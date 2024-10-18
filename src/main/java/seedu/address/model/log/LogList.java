package seedu.address.model.log;

import static java.util.Objects.requireNonNull;

import java.util.List;

import org.junit.platform.commons.util.ToStringBuilder;

import javafx.collections.ObservableList;

public class LogList implements ReadOnlyLogList {

    private final UniqueLogList logs;

    public LogList() {
        logs = new UniqueLogList();
    }

    /**
     * Creates a Log List using the logs in the {@code toBeCopied}
     */
    public LogList(ReadOnlyLogList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setLogs(List<Log> logs) {
        this.logs.setLogs(logs);
    }

    private void resetData(ReadOnlyLogList newData) {
        requireNonNull(newData);

        setLogs(newData.getLogList());
    }


    /// log-level operations

    /**
     * Returns true if a person with the same identity as {@code log} exists in the log list.
     */
    public boolean hasLog(Log log) {
        requireNonNull(log);
        return logs.contains(log);
    }

    /**
     * Adds a log to the address book.
     * The log must not already exist in the session log book
     */
    public void addLog(Log p) {
        logs.add(p);
    }

    /**
     * Replaces the given log {@code log} in the list with {@code editedLog}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedLog} must not be the same as another existing log in the log list.
     */
    public void setLog(Log target, Log editedLog) {
        requireNonNull(editedLog);

        logs.setLog(target, editedLog);
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("logs", logs)
//                .toString();
//    }

    @Override
    public ObservableList<Log> getLogList() {
        return logs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LogList)) {
            return false;
        }

        LogList otherLogList = (LogList) other;
        return logs.equals(otherLogList.logs);
    }

    @Override
    public int hashCode() {
        return logs.hashCode();
    }
}
