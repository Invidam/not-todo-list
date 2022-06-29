package exception.token;



public class InCorrectAccessTokenException extends RuntimeException {
    public InCorrectAccessTokenException(String message) {
        super(message);
    }
}
