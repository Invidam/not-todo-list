package DTO.User;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class SignupUserDTO {
    @NotBlank(message = "SignupUserDTO, Account is empty")
    private String account;
    @NotBlank(message = "SignupUserDTO, Nickname is empty")
    private String nickname;
    @NotBlank(message = "SignupUserDTO, Password is empty")
    private String password;

}
