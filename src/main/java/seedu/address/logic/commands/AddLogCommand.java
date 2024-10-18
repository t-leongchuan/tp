package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.log.Log;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Person;

/**
 * Adds a Session Log to a Patient.
 */
public class AddLogCommand extends Command {

    public static final String COMMAND_WORD = "addlog";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a log to the person identified by the Identification Number.\n"
            + "Parameters: i/NRIC d/APPOINTMENT_DATE e/ENTRY\n"
            + "Example: " + COMMAND_WORD + " i/S6285715C d/2024-10-17 l/Discussed treatment options.";

    public static final String MESSAGE_ADD_LOG_SUCCESS = "Added log to Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with ID %1$s not found.";

    private final IdentityNumber identityNumber;
    private final Log log;

    public AddLogCommand(IdentityNumber identityNumber, Log log) {
        requireAllNonNull(identityNumber, log);
        this.identityNumber = identityNumber;
        this.log = log;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToUpdate = null;

        // Find the person by identity number
        for (Person person : lastShownList) {
            if (person.getIdentityNumber().equals(identityNumber)) {
                personToUpdate = person;
                break;
            }
        }

        // If person was not found, throw an exception
        if (personToUpdate == null) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, identityNumber));
        }

        model.addLogToPerson(personToUpdate, log);

        return new CommandResult(String.format(MESSAGE_ADD_LOG_SUCCESS, personToUpdate.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLogCommand)) {
            return false;
        }

        AddLogCommand otherAddLogCommand = (AddLogCommand) other;
        return identityNumber.equals(otherAddLogCommand.identityNumber) && log.equals(otherAddLogCommand.log);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("identityNumber", identityNumber)
                .add("log", log)
                .toString();
    }
}
