package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityNumber;

import static java.util.Objects.requireNonNull;

/**
 * Lists all persons in the address book to the user.
 */
public class ListLogsCommand extends Command {

    public static final String COMMAND_WORD = "logs";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_SUCCESS = "Listed all logs for: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all logs of a person identified by the NRIC.\n"
            + "Parameters: NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567A";
    private final IdentityNumber identityNumber;

    /**
     * Creates a ListLogsCommand to list the logs of the specified person
     */
    public ListLogsCommand(IdentityNumber id) {
        this.identityNumber = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //TODO: Handle behaviour later
        //model.updateFilteredPersonList(this.identityNumber);
        return new CommandResult(MESSAGE_SUCCESS + this.identityNumber.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ListLogsCommand)) {
            return false;
        }
        ListLogsCommand e = (ListLogsCommand) other;

        // Since ListLogsCommand relies soley on id, last check to compare only id
        return identityNumber.equals(e.identityNumber);
    }
}
