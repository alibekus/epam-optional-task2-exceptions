package kz.akbar.exception;

import java.io.FileNotFoundException;

public class NumberFileExistException extends FileNotFoundException {

    private String message;

    public NumberFileExistException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
