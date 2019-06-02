package kz.akbar.exception;

public class DoubleNumberRangeException extends Exception {
    private String message;

    public DoubleNumberRangeException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
