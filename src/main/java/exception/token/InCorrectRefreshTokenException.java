package exception.token;



public class InCorrectRefreshTokenException extends RuntimeException {
    public InCorrectRefreshTokenException(String message) {
        super(message);
    }
}
