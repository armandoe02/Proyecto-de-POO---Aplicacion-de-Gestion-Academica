package shared.exceptions;

public class SheduleConflictException extends ValidationException {
    public SheduleConflictException(String message) {
        super(message);
    }
}
