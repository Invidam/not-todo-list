package enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuccessStatus
{
    //Auth
    LOGOUT("LOGOUT_SUCCESS", "success at logout."),
    //User
    SIGNUP("SIGNUP_SUCCESS", "success at sign up."),
    UPDATE_USER("UPDATE_USER_SUCCESS", "success at update user."),
    WITHDRAW_USER("WITHDRAW_USER_SUCCESS", "success at withdraw user."),
    //Item
    CREATE_ITEM("CREATE_ITEM_SUCCESS", "success at create item."),
    UPDATE_ITEM("UPDATE_ITEM_SUCCESS", "success at update item."),
    WITHDRAW_ITEM("WITHDRAW_ITEM_SUCCESS", "success at withdraw item."),
    UPDATE_ITEM_STATUS("UPDATE_ITEM_STATUS_SUCCESS", "success at update item status."),

    //Emotion
    CREATE_EMOTION_REL("CREATE_EMOTION_REL_SUCCESS", "success at create  emotion relation."),
    DELETE_EMOTION_REL("DELETE_EMOTION_REL_SUCCESS", "success at delete emotion relation.");


    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    private final String type;
    private final String message;
    private SuccessStatus(String type, String message) {
        this.type = type;
        this.message = message;
    };
}