package domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Getter
@Setter
public class User {

    private long id;
    @NotEmpty(message = "Account is Empty.")
    private String account;
    @NotEmpty(message = "Password is Empty.")
    private String password;
    @NotEmpty(message = "Nickname is Empty.")
    private String nickname;
    private Date createdAt;
    private boolean isDeleted;
    private String refreshToken;

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}
