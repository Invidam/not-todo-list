package DTO.User;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateUserDTO {
    @NotBlank(message = "UpdateUserDTO, ID is empty")
    private long id;
    @NotBlank(message = "UpdateUserDTO, Account is empty")
    private String account;
    @NotBlank(message = "UpdateUserDTO, Nickname is empty")
    private String nickname;
    @NotBlank(message = "UpdateUserDTO, Password is empty")
    private String password;

    private String updatedPassword = "";
}
