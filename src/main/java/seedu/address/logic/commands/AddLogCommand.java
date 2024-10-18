package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
            + "Parameters: p/PERSON d/APPOINTMENT_DATE e/ENTRY\n"
            + "Example: " + COMMAND_WORD + " i/S1234567Z d/2024-10-17 e/Discussed treatment options.";

    public static final String MESSAGE_SUCCESS = "Added log to Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with ID %1$s not found.";

    private final Person person;
    private final Log log;

    public AddLogCommand(Person person, Log log) {
        requireNonNull(person);
        requireNonNull(log);
        this.person = person;
        this.log = log;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(person)) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, person));
        }

        model.addLogToPerson(person, log);
        return new CommandResult(String.format(MESSAGE_SUCCESS, person));
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
        return person.equals(otherAddLogCommand.person) && log.equals(otherAddLogCommand.log);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("identityNumber", person)
                .add("log", log)
                .toString();
    }
}
