package exception.item;

public class ItemNotPassedDeadlineException extends RuntimeException {
    public ItemNotPassedDeadlineException(String message) {
        super(message);
    }
}
