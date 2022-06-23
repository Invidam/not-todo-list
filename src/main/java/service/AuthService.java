package service;

import DTO.IdAndTokenDTO;
import DTO.LoginUserDTO;
import DTO.TokenDTO;
import domain.User;

public interface AuthService {

    TokenDTO login(LoginUserDTO loginUserDTO);
    User verify(TokenDTO tokenDTO);
//    User logout(String token);

    //

    TokenDTO refreshToken(TokenDTO tokenDTO);
}