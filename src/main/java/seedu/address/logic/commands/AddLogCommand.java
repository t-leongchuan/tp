package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
            + "Parameters: i/IDENTIFICATION_NUMBER d/APPOINTMENT_DATE e/ENTRY\n"
            + "Example: " + COMMAND_WORD + " i/S1234567Z d/2024-10-17 e/Discussed treatment options.";

    public static final String MESSAGE_SUCCESS = "Added log to Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with ID %1$s not found.";
    public static final String MESSAGE_DUPLICATE_LOG = "This log already exists for the person.";

    private final IdentityNumber identityNumber;
    private final Log log;

    public AddLogCommand(IdentityNumber identityNumber, Log log) {
        requireNonNull(identityNumber);
        requireNonNull(log);
        this.identityNumber = identityNumber;
        this.log = log;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Placeholder
        return new CommandResult(String.format(MESSAGE_SUCCESS, identityNumber));
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
