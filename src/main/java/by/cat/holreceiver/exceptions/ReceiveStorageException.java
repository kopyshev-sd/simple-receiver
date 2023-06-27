package by.cat.holreceiver.exceptions;

public class ReceiveStorageException extends RuntimeException {
    public ReceiveStorageException(String message) {
        super(message);
    }

    public ReceiveStorageException(Throwable cause) {
        super(cause);
    }

    public ReceiveStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
