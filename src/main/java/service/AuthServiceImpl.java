package service;

import DTO.Token.TokenDTO;
import DTO.User.UserAndTokenDTO;
import DTO.User.UserIdAndTokenDTO;
import auth.Encryption;
import auth.JwtToken;
import com.auth0.jwt.exceptions.JWTVerificationException;
import domain.User;
import domainGroup.User.LoginUser;
import enums.ExceptionMessage;
import exception.token.InCorrectRefreshTokenException;
import exception.token.RefreshTokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.AuthMapper;
import repository.UserMapper;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService{

    private final JwtToken jwtToken;
    private final Encryption encryption;

    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    @Autowired
    public AuthServiceImpl(JwtToken jwtToken, Encryption encryption, UserMapper userMapper, AuthMapper authMapper) {
        this.jwtToken = jwtToken;
        this.encryption = encryption;
        this.userMapper = userMapper;
        this.authMapper = authMapper;
    }

    @Override
    public UserAndTokenDTO getLoggedInUserAndToken(LoginUser loginUser) {
        try {

            User loggedInUser = userMapper.getUserByAccount(loginUser.getAccount());
            if(loggedInUser.getIsDeleted()) throw new AccountExpiredException(ExceptionMessage.ACCOUNT_EXPIRED.getMessage());
            if (!encryption.isMatch(loginUser.getPassword(), loggedInUser.getPassword()))
                throw new BadCredentialsException(ExceptionMessage.WRONG_PASSWORD.getMessage());

            return new UserAndTokenDTO(loggedInUser.getId(), jwtToken.create(loggedInUser));
        }
        catch(NullPointerException nullPointerException) {
            //DB에 유저를 찾지 못한 경우
            throw new UsernameNotFoundException(ExceptionMessage.LOGIN_USER_NOT_FOUND.getMessage());
        }
    }
    @Override
    public TokenDTO getTokenFor(User user) {
        return jwtToken.create(user);
    }
    @Override
    public void updateUserRefreshToken(UserIdAndTokenDTO userIdAndTokenDTO) {
        authMapper.updateRefreshTokenById(userIdAndTokenDTO);
    }

    @Override
    public long verifyRefreshToken(String inputtedRefreshToken) {
        try {
            return jwtToken.verifyRefreshToken(inputtedRefreshToken);
        }
        catch(JWTVerificationException e) {
            throw new RefreshTokenExpiredException(e.getMessage());
        }
    }

    @Override
    public void checkRefreshToken(UserIdAndTokenDTO userIdAndTokenDTO) {
        try {
            String dbRefreshToken = authMapper.getRefreshTokenById(userIdAndTokenDTO.getId());
            if (dbRefreshToken.isEmpty() || !(Objects.equals(dbRefreshToken, userIdAndTokenDTO.getRefreshToken())))
                throw new InCorrectRefreshTokenException(ExceptionMessage.INCORRECT_REFRESH_TOKEN.getMessage());
        }
        catch(NullPointerException nullPointerException) {
            //DB에 유저(토큰으로 인한)를 찾지 못한 경우
            throw new UsernameNotFoundException(ExceptionMessage.TOKEN_USER_NOT_FOUND.getMessage());
        }
    }

    @Override
    public TokenDTO refreshToken(long id) {
        TokenDTO newTokenDTO = jwtToken.create(userMapper.getUserById(id));
        updateUserRefreshToken(new UserIdAndTokenDTO(id, newTokenDTO.getRefreshToken()));
        return newTokenDTO;
    }
}