package service.exception;

public class WrongDirrectoryException extends RuntimeException {
    public WrongDirrectoryException(String message) {
        super(message);
    }
}
