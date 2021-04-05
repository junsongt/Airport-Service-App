package exception;

public class EmptySearchResultException extends Exception {

    public EmptySearchResultException() {
        super("Sorry! No flight found!");
    }
}
