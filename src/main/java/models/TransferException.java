package models;

/**
 * Exception if don't able to transfer money
 */
public class TransferException extends Exception {

    /**
     * Constructor
     * @param message
     */
    public TransferException(String message) {
        super(message);
    }
}
