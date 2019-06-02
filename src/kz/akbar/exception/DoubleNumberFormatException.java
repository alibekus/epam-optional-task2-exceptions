package kz.akbar.exception;

import java.util.logging.Logger;

public class DoubleNumberFormatException extends NumberFormatException {
    private String message;
    private static final Logger LOG = Logger.getLogger(DoubleNumberFormatException.class.getName());

    public DoubleNumberFormatException(String message) {
        super(message);
        this.message = message;
    }

    public void getMessage(String message) {
        LOG.info(this.getMessage() + ": " + message);
    }
}
