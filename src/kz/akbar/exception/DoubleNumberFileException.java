package kz.akbar.exception;

public class DoubleNumberFileException extends Exception {

    private String message;

    public DoubleNumberFileException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
