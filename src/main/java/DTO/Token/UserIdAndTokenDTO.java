package DTO.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class UserIdAndTokenDTO {
    @NotEmpty(message = "UserIdAndTokenDTO's Id is empty.")
    private long id;
    @NotEmpty(message = "UserIdAndTokenDTO's refreshToken is empty.")
    private String refreshToken;
}
