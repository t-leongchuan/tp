package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.log.AppointmentDate;
import seedu.address.model.log.Log;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Person;

/**
 * Adds a log to the person identified by their Identity Number in the address book.
 */
public class AddLogCommand extends Command {

    public static final String COMMAND_WORD = "addlog";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a log to the person identified by the Identification Number in the person list.\n"
            + "Parameters: i/ IDENTIFICATION NUMBER (must be 9 characters long) l/ APPT DATE|ENTRY\n"
            + "Format of APPT DATE: yyyy-MM-dd\n"
            + "Example: " + COMMAND_WORD + " i/S6285715C l/ 2024-10-17|Doctor's appointment\n";

    public static final String MESSAGE_ADD_LOG_SUCCESS = "Added log for Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with ID %1$s not found.";


    private final IdentityNumber identityNumber;
    private final Log log;

    /**
     * Creates an AddLogCommand to add the specified log to the person.
     */
    public AddLogCommand(IdentityNumber identityNumber, String date, String entry) {
        this.identityNumber = identityNumber;
        AppointmentDate appointmentDate = new AppointmentDate(date);
        this.log = new Log(appointmentDate, entry); // Assuming Log class has a constructor accepting date and entry
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

        // Create a new Set of logs that includes the new log
        Set<Log> updatedLogs = new HashSet<>(personToUpdate.getLogs());
        updatedLogs.add(log);

        // Create a new updated person with the additional log
        Person updatedPerson = new Person(
                personToUpdate.getName(),
                personToUpdate.getIdentityNumber(),
                personToUpdate.getPhone(),
                personToUpdate.getEmail(),
                personToUpdate.getAddress(),
                personToUpdate.getTags(),
                updatedLogs // Updated logs set
        );

        // Update the model with the new person (with the added log)
        model.setPerson(personToUpdate, updatedPerson);

        return new CommandResult(String.format(MESSAGE_ADD_LOG_SUCCESS, updatedPerson.getName()));
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
