package exception;

public class InvalidSeatException extends Exception {

    public InvalidSeatException() {
        super("Invalid seat coordinates!");
    }
}
