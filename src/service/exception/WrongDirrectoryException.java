package service.exception;

/**
 * Created by Artem on 08.03.2016.
 */
public class WrongDirrectoryException extends RuntimeException {
    public WrongDirrectoryException(String message) {
        super(message);
    }
}
