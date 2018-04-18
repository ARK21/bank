package models;

/**
 * Exception if don't able to transfer money
 */
public class TransferException extends Exception {

    public TransferException(String message) {
        super(message);
    }
}
