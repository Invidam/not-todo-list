package DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO extends domain.User{
    private String updatedPassword;
}
