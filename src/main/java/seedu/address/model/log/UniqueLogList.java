package seedu.address.model.log;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.log.exceptions.DuplicateLogException;
import seedu.address.model.log.exceptions.LogNotFoundException;

/**
 * A list of logs that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueLogList implements Iterable<Log> {

    private final ObservableList<Log> internalList = FXCollections.observableArrayList();
    private final ObservableList<Log> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent log as the given argument.
     */
    public boolean contains(Log toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLog);
    }


    /**
     * Adds a log to the list.
     * The log must not already exist in the list.
     */
    public void add(Log toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLogException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the log {@code target} in the list {@code editedLog}.
     * {@code target} must exist in the list.
     * The log details of {@code editedlog} must not be the same as another existing log.
     */
    public void setLog(Log target, Log editedLog) {
        requireAllNonNull(target, editedLog);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LogNotFoundException();
        }

        if (!target.isSameLog(editedLog) && contains(editedLog)) {
            throw new DuplicateLogException();
        }

        internalList.set(index, editedLog);
    }

    /**
     * Removes the equivalent log from the list.
     * The log must exist in the list.
     */
    public void remove(Log toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LogNotFoundException();
        }
    }

    public void setLogs(UniqueLogList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code logs}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setLogs(List<Log> logs) {
        requireAllNonNull(logs);
        if (!logsAreUnique(logs)) {
            throw new DuplicateLogException();
        }

        internalList.setAll(logs);
    }

    /**
     * Returns the backing list as an unmofiiable {@code ObservableList}
     */
    public ObservableList<Log> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Log> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UniqueLogList)) {
            return false;
        }

        UniqueLogList otherList = (UniqueLogList) other;
        return internalList.equals(otherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean logsAreUnique(List<Log> logs) {
        for (int i = 0; i < logs.size() - 1; i++) {
            for (int j = i + 1; j < logs.size(); j++) {
                if (logs.get(i).isSameLog(logs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
