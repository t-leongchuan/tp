package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListLogCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdentityNumber;

public class ListLogCommandParser implements Parser<ListLogCommand> {

    public ListLogCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            IdentityNumber identityNumber = ParserUtil.parseIdentityNumber(args);
            return new ListLogCommand(identityNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListLogCommand.MESSAGE_USAGE), pe);
        }
    }
}
