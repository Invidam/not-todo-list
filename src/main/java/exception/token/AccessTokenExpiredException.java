package exception.token;


import com.auth0.jwt.exceptions.TokenExpiredException;

//db에 토큰 없음, db에 토큰이랑 다름, 유효하지 안흥 토큰임
public class AccessTokenExpiredException extends TokenExpiredException {
    public AccessTokenExpiredException(String message) {
        super(message);
    }
}
