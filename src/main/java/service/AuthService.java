package service;

import DTO.User.LoginUserDTO;
import DTO.Token.TokenDTO;

import javax.security.auth.RefreshFailedException;

public interface AuthService {

    TokenDTO login(LoginUserDTO loginUserDTO);

    TokenDTO refreshToken(String refreshToken);
}