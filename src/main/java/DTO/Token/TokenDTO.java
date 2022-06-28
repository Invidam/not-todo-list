package DTO.Token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class TokenDTO {

    private String accessToken;
    @NotEmpty(message = "Refresh Token is Empty.")
    private String refreshToken;
    public final String tokenType = "bearer";

    public TokenDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
