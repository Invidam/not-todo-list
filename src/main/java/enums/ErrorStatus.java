package enums;

import org.springframework.http.HttpStatus;


public enum ErrorStatus
{
    //Auth
    ACCOUNT_EXPIRED("ACCOUNT_EXPIRED", HttpStatus.UNAUTHORIZED, -101),
    WRONG_PASSWORD("WRONG_PASSWORD",HttpStatus.UNAUTHORIZED,-102),
    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND,-100),
    USER_MISMATCH("USER_MISMATCH",HttpStatus.FORBIDDEN,-103),
    REFRESH_TOKEN_IS_EXPIRED("REFRESH_TOKEN_IS_EXPIRED",HttpStatus.UNAUTHORIZED,-110),
    INCORRECT_REFRESH_TOKEN("INCORRECT_REFRESH_TOKEN",HttpStatus.FORBIDDEN,-111),
    TOKEN_NOT_CREATED("TOKEN_NOT_CREATED",HttpStatus.UNAUTHORIZED,-112),
    TOKEN_NOT_VERIFIED("TOKEN_NOT_VERIFIED",HttpStatus.UNAUTHORIZED,-113),
    TOKEN_NOT_DECODED("TOKEN_NOT_DECODED",HttpStatus.UNAUTHORIZED,-114),
    ACCESS_TOKEN_EXPIRED("ACCESS_TOKEN_EXPIRED",HttpStatus.UNAUTHORIZED,-115),
    INCORRECT_ACCESS_TOKEN("INCORRECT_ACCESS_TOKEN",HttpStatus.UNAUTHORIZED,-116),
    TOKEN_NOT_EXIST("TOKEN_NOT_EXIST",HttpStatus.UNAUTHORIZED,-120),

    //User
    ALREADY_EXIST_USER("ALREADY_EXIST_USER",HttpStatus.BAD_REQUEST,-200),

    //Item
    ITEM_NOT_FOUND("ITEM_NOT_FOUND",HttpStatus.NOT_FOUND,-300),
    ITEM_NOT_PASSED_DEADLINE("ITEM_NOT_PASSED_DEADLINE", HttpStatus.BAD_REQUEST,-301),

    //Emotion
    ALREADY_EXIST_EMOTION_RELATION("ALREADY_EXIST_EMOTION_RELATION",HttpStatus.BAD_REQUEST,-400),
    EMOTION_RELATION_NOT_FOUND("EMOTION_RELATION_NOT_FOUND",HttpStatus.NOT_FOUND,-401),

    //REST
    UNEXPECTED_ERROR("UNEXPECTED_ERROR",HttpStatus.INTERNAL_SERVER_ERROR,-0);

    public String getType() {
        return type;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public int getCode() {
        return code;
    }
    private final String type;
    private final HttpStatus status;
    private final int code;
    ErrorStatus(String type, HttpStatus status, int code) {
        this.type = type;
        this.status = status;
        this.code = code;
    };
}


