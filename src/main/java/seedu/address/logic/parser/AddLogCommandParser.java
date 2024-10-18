package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IDENTITY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOG;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddLogCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.log.AppointmentDate;
import seedu.address.model.log.Log;
import seedu.address.model.person.IdentityNumber;

public class AddLogCommandParser implements Parser<AddLogCommand> {

    public AddLogCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_IDENTITY_NUMBER, PREFIX_APPOINTMENT_DATE, PREFIX_LOG);

        if (!arePrefixesPresent(argMultimap, PREFIX_IDENTITY_NUMBER, PREFIX_APPOINTMENT_DATE, PREFIX_LOG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLogCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_IDENTITY_NUMBER, PREFIX_APPOINTMENT_DATE, PREFIX_LOG);
        IdentityNumber identityNumber = ParserUtil.parseIdentityNumber(
                argMultimap.getValue(PREFIX_IDENTITY_NUMBER).get());
        AppointmentDate appointmentDate = ParserUtil.parseAppointmentDate(
                argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
        Log log = ParserUtil.parseLog(argMultimap.getValue(PREFIX_LOG).get(),
                argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());

        return new AddLogCommand(identityNumber, log);
    }

    /**
     * Returns true if none of the prefixes contain empty values in the given ArgumentMultimap.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
