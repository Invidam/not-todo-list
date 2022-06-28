package service;

import DTO.Token.UserIdAndTokenDTO;
import DTO.User.LoginUserDTO;
import DTO.Token.TokenDTO;
import auth.Encryption;
import auth.JwtToken;
import domain.User;
import exception.token.InCorreftRefreshTokenException;
import exception.token.RefreshTokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.AuthMapper;
import repository.UserMapper;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final JwtToken jwtToken;
    @Autowired
    private final Encryption encryption;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthMapper authMapper;

    public AuthServiceImpl(JwtToken jwtToken, Encryption encryption) {
        this.jwtToken = jwtToken;
        this.encryption = encryption;
    }

    @Override
    public TokenDTO login(LoginUserDTO loginUserDTO) {
        try {
            User loggedInUser = userMapper.getUserByAccount(loginUserDTO.getAccount());
            if(loggedInUser.getIsDeleted()) throw new AccountExpiredException("User is Deleted");
            if (!encryption.isMatch(loginUserDTO.getPassword(), loggedInUser.getPassword()))
                throw new BadCredentialsException("Password is wrong.");

            TokenDTO tokenDTO = jwtToken.create(loggedInUser);
            authMapper.updateRefreshTokenById(new UserIdAndTokenDTO(loggedInUser.getId(), tokenDTO.getRefreshToken()));

            return tokenDTO;
        }
        catch(NullPointerException nullPointerException) {
            //DB에 유저를 찾지 못한 경우
            throw new UsernameNotFoundException("Login User not found");
        }
    }

//    @Override
//    public User logout(String accessToken) {
//        try {
//            UserInfoDTO userInfoDTO = jwtToken.verify(accessToken);
//
//            User loggedInUser = userMapper.getUserById(userInfoDTO.getId());
//            if(loggedInUser.getRefreshToken().isEmpty()) throw new AccountExpiredException("User is Deleted");
//            authMapper.updateRefreshTokenById(new UserIdAndTokenDTO(loggedInUser.getId(),""));
////            return tokenDTO;
//        }
//        catch(NullPointerException nullPointerException) {
//            //DB에 유저를 찾지 못한 경우
//            throw new UsernameNotFoundException("Login User not found");
//        }
////        return jwttoken.verify(token);
//        //db에 있어야 함
//        //db에 있는거 삭제
//        //ok sign 보내기
//    }



    @Override
    public TokenDTO refreshToken(@NotEmpty(message = "RefreshToken is empty.") String inputtedRefreshToken) {
        try {
            long id = jwtToken.getIdInRefreshToken(inputtedRefreshToken);
            String dbRefreshToken = authMapper.getRefreshTokenById(id);

            if (jwtToken.isExpiredRefreshToken(inputtedRefreshToken))
                throw new RefreshTokenExpiredException("Refresh Token is expired.");
            if (dbRefreshToken.isEmpty() || !(Objects.equals(dbRefreshToken, inputtedRefreshToken)))
                throw new InCorreftRefreshTokenException("Inputted Refresh Token is not equal to DB's Refresh Token.");

            TokenDTO newTokenDTO = jwtToken.create(userMapper.getUserById(id));
            authMapper.updateRefreshTokenById(new UserIdAndTokenDTO(id, newTokenDTO.getRefreshToken()));

            return newTokenDTO;
            //토큰에러 처리하기
            //JWTDecodeException
        }
        catch(NullPointerException nullPointerException) {
            //DB에 유저(토큰으로 인한)를 찾지 못한 경우
            throw new UsernameNotFoundException("Token's User not found");
        }
    }

}