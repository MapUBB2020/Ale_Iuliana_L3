package Exceptions;

public class WrongInput extends Exception {
    public WrongInput(String message) {
        super(message);
        System.out.println(message);
    }
}
