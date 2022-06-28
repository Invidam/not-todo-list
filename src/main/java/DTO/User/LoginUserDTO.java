package DTO.User;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class LoginUserDTO {
    @NotBlank(message = "LoginUserDTO, Account is empty")
    private String account;
    @NotBlank(message = "LoginUserDTO, Password is empty")
    private String password;
}
