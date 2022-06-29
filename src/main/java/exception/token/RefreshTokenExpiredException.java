package exception.token;


import com.auth0.jwt.exceptions.TokenExpiredException;

public class RefreshTokenExpiredException extends TokenExpiredException {
    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
