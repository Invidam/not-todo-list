package service;

import auth.Encryption;
import auth.JwtToken;
import domain.User;
import domainGroup.User.Rank;
import domainGroup.User.UserRepresentInfo;
import enums.ExceptionMessage;
import exception.user.UserAlreadyExistException;
import exception.user.UserMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    private final JwtToken jwtToken;
    private final Encryption encryption;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(JwtToken jwtToken, Encryption encryption, UserMapper userMapper) {
        this.jwtToken = jwtToken;
        this.encryption = encryption;
        this.userMapper = userMapper;
    }

    @Override
    public void checkExistInfo(User user) {
        if(userMapper.isExistAccount(user.getAccount()))
            throw new UserAlreadyExistException(ExceptionMessage.INPUT_ACCOUNT_ALREADY_EXISTS.getMessage());
        if(userMapper.isExistNickname(user.getNickname()))
            throw new UserAlreadyExistException(ExceptionMessage.INPUT_NICKNAME_ALREADY_EXISTS.getMessage());
    }

    @Override
    public void createUser(User user) {
        user.setPassword(encryption.generate(user.getPassword()));
        userMapper.createUser(user);
    }

    @Override
    public UserRepresentInfo getUserRepresentInfoById(long id) {
        if(!userMapper.isExistUser(id))
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        return userMapper.getUserRepresentInfoById(id);
    }

    @Override
    public void checkUserById(long id, String accessToken) {

        if(!userMapper.isExistUser(id))
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        if(id != jwtToken.verifyAccessToken(accessToken))
            throw new UserMismatchException(ExceptionMessage.TOKEN_USER_MISMATCH.getMessage());
    }

    @Override
    public void updateUser(User user, String updatePassword) {
        String dbHashedPassword = userMapper.getUserById(user.getId()).getPassword();
        if(!encryption.isMatch(user.getPassword(),dbHashedPassword))
            throw new BadCredentialsException(ExceptionMessage.USER_MISMATCH.getMessage());

        if(!updatePassword.isEmpty())
            user.setPassword(encryption.generate(updatePassword));
        else
            user.setPassword(dbHashedPassword);

        userMapper.updateUser(user);
    }

    @Override
    public void withdrawUser(long id) {
        userMapper.withdrawUser(id);
    }

    @Override
    public Rank getRank() {
        return userMapper.getRank();
    }
}
