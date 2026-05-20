package shared.exceptions;

public class QuotaUnavailableException extends ValidationException{
    public QuotaUnavailableException(String message) {
        super(message);
    }
}
