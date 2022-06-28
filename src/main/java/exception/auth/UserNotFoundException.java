package exception.auth;



//db에 토큰 없음, db에 토큰이랑 다름, 유효하지 안흥 토큰임
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
