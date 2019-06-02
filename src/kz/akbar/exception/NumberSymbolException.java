package kz.akbar.exception;

public class NumberSymbolException extends Exception {

    private String message;

    public NumberSymbolException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(String message) {
        return message;
    }
}
