package domainGroup.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
public class LoginUser {
    @NotBlank(message = "LoginUser, Account is empty")
    private String account;
    @NotBlank(message = "LoginUser, Password is empty")
    private String password;
}
