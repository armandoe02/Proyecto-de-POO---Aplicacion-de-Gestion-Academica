package shared.exceptions;

public class UnauthorizedActionException extends ValidationException {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
