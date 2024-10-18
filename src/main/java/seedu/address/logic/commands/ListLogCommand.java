package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Person;

public class ListLogCommand extends Command {
    public static final String COMMAND_WORD = "listlog";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the log entries.\n"
            + "Parameters: i/NRIC\n"
            + "Example: " + COMMAND_WORD + " i/S6285715C";

    private final IdentityNumber identityNumber;

    public static final String MESSAGE_LIST_LOG_SUCCESS = "Listed Logs of Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with ID %1$s not found.";

    public ListLogCommand(IdentityNumber identityNumber) {
        requireAllNonNull(identityNumber);
        this.identityNumber = identityNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        int personIndex = -1;

        // Find the person's index in the list by identity number
        for (Person person : lastShownList) {
            if (identityNumber.equals(person.getIdentityNumber())) {
                personIndex = lastShownList.indexOf(person);
            }
        }

        // If person was not found, throw an exception
        if (personIndex == -1) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, identityNumber));
        }

        model.getLogList(personIndex);
        return new CommandResult(String.format(MESSAGE_LIST_LOG_SUCCESS, identityNumber), false,
                false, true, personIndex);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListLogCommand)) {
            return false;
        }

        ListLogCommand otherListLogCommand = (ListLogCommand) other;
        return identityNumber.equals(otherListLogCommand.identityNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("identityNumber", identityNumber)
                .toString();
    }
}
