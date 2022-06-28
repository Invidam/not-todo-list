package exception.token;



//db에 토큰 없음, db에 토큰이랑 다름, 유효하지 안흥 토큰임
public class InCorreftRefreshTokenException extends RuntimeException {
    public InCorreftRefreshTokenException(String message) {
        super(message);
    }
}

//거기에서만 된다. 어디? 컨트롤러..ㅋ