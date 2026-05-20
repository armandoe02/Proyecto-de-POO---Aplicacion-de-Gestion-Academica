package shared.exceptions;

public class EntityNotFoundException extends ValidationException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
