package Exceptions;

/**
 * Class for the wrong input by the user
 * For example if the required data type is int and the user gives a string input
 */
public class WrongInput extends Exception {
    public WrongInput(String message) {
        super(message);
        System.out.println(message);
    }
}
