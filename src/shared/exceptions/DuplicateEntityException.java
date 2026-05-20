package shared.exceptions;

public class DuplicateEntityException extends ValidationException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
