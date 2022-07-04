package enums;


public enum ExceptionMessage
{
    //Auth

    ACCOUNT_EXPIRED("User is Deleted"),
    WRONG_PASSWORD("Password is wrong."),
    USER_NOT_FOUND("User not found"),
    LOGIN_USER_NOT_FOUND( "Login User not found"),
    TOKEN_USER_NOT_FOUND("Token User not found"),
    INPUT_USER_NOT_FOUND("Inputted User is not exists."),
    INCORRECT_REFRESH_TOKEN("Inputted Refresh Token is not equal to DB's Refresh Token."),
    AUTH_HEADER_IS_EMPTY("Authorization Header Authorization Is Empty"),

    //User
    INPUT_ACCOUNT_ALREADY_EXISTS("Inputted Account is already exist."),
    INPUT_NICKNAME_ALREADY_EXISTS("Inputted Nickname is already exist."),
    USER_MISMATCH("Does Not Match the Existing Password."),
    TOKEN_USER_MISMATCH("Token's User is not equal to Inputted User."),

    //Item
    INPUT_ITEM_NOT_FOUND("Inputted Path's Item is not found."),
    DELETE_ITEM_NOT_FOUND("To Delete Item is not exists."),
    ITEM_NOT_PASSED_DEADLINE("To Update Item not passed deadline."),

    //Emotion
    EMOTION_REL_IS_EXIST("Emotion Relation is already exist."),
    EMOTION_REL_NOT_FOUND("Emotion Relation is not found."),

    //REST
    UNEXPECTED_ERROR("Server's error.");


    public String getMessage() {
        return message;
    }
    private final String message;
    ExceptionMessage(String message) {
        this.message = message;
    }
}


