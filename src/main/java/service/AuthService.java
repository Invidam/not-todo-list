package service;

import DTO.Token.TokenDTO;
import DTO.User.UserAndTokenDTO;
import DTO.User.UserIdAndTokenDTO;
import domain.User;
import domainGroup.User.LoginUser;

public interface AuthService {


    UserAndTokenDTO getLoggedInUserAndToken(LoginUser loginUser);

    TokenDTO getTokenFor(User user);

    void updateUserRefreshToken(UserIdAndTokenDTO userIdAndTokenDTO);


    long verifyRefreshToken(String inputtedRefreshToken);

    void checkRefreshToken(UserIdAndTokenDTO userIdAndTokenDTO);

    TokenDTO refreshToken(long id);
}