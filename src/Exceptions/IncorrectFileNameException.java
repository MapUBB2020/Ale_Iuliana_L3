package Exceptions;

/**
 * Class for exceptions in case the name of the file is incorrect
 */
public class IncorrectFileNameException extends Exception {
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}
