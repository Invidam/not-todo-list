package DTO.User;

import DTO.Token.TokenDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAndTokenDTO {
    @NotEmpty(message = "UserAndTokenDTO's Id is empty.")
    private long id;
    @NotEmpty(message = "UserAndTokenDTO's refreshToken is empty.")
    private TokenDTO token;
}
