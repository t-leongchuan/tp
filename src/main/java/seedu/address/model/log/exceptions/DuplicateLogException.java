package seedu.address.model.log.exceptions;

/**
 * Signals that the operation will result in duplicate logs.
 */
public class DuplicateLogException extends RuntimeException {
  public DuplicateLogException() {
    super("Operation would result in duplicate logs");
  }
}