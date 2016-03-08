package service.exception;

/**
 * Created by Artem on 08.03.2016.
 */
public class WrongDestinationException extends RuntimeException{
    public WrongDestinationException(String message) {
        super(message);
    }
}
