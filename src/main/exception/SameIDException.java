package exception;

public class SameIDException extends Exception {

    public SameIDException() {
        super("This ID has already booked this flight!");
    }
}
