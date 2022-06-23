package service;

import DTO.IdAndTokenDTO;
import DTO.LoginUserDTO;
import DTO.TokenDTO;
import auth.JwtToken;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuthMapper;
import repository.UserMapper;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService{

    private final JwtToken jwtToken;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthMapper authMapper;

    public AuthServiceImpl(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public TokenDTO login(LoginUserDTO loginUserDTO) {
        System.out.println("LOGIN USER: "+loginUserDTO.getAccount() +  loginUserDTO.getPassword());
        User loggedInUser = userMapper.getUserInLogin(loginUserDTO);
        System.out.println(loggedInUser.getId());

        TokenDTO tokenDTO = jwtToken.create(loggedInUser, false);
        authMapper.updateRefreshTokenById(new IdAndTokenDTO(loggedInUser.getId(),tokenDTO.getRefreshToken()));

        return tokenDTO;
    }

    //    @Override
//    public User logout(String token) {
//        return jwttoken.verify(token);
//    }

    @Override
    public User verify(TokenDTO tokenDTO) {
        return jwtToken.verify(tokenDTO);
    }




    @Override
    public TokenDTO refreshToken(TokenDTO tokenDTO) {
        Long id = jwtToken.getIdInRefreshToken(tokenDTO);
        String dbRefreshToken = authMapper.getRefreshTokenById(id);

        if(!jwtToken.isValidRefreshToken(tokenDTO) || !(Objects.equals(dbRefreshToken, tokenDTO.getRefreshToken())))
            throw new Error("토큰 유효하지 않거나 db랑 다름");

        TokenDTO newTokenDTO = jwtToken.create(userMapper.getUserById(id), false);
        authMapper.updateRefreshTokenById(new IdAndTokenDTO(id,newTokenDTO.getRefreshToken()));

        return newTokenDTO;
    }

}