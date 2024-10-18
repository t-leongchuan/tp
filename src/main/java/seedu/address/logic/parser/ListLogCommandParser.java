package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IDENTITY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.ListLogCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdentityNumber;

public class ListLogCommandParser implements Parser<ListLogCommand> {

    public ListLogCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_IDENTITY_NUMBER);

        if (!argMultimap.getValue(PREFIX_IDENTITY_NUMBER).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListLogCommand.MESSAGE_USAGE));
        }

        IdentityNumber identityNumber = ParserUtil.parseIdentityNumber(
                argMultimap.getValue(PREFIX_IDENTITY_NUMBER).get());

        return new ListLogCommand(identityNumber);
    }
}
