package service;

import DTO.User.UpdateUserDTO;
import DTO.User.UserRepresentInfoDTO;
import auth.Encryption;
import auth.JwtToken;
import domain.User;
import exception.user.UserAlreadyExistException;
import exception.user.UserMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final JwtToken jwtToken;

    @Autowired
    private final Encryption encryption;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(JwtToken jwtToken, Encryption encryption) {
        this.jwtToken = jwtToken;
        this.encryption = encryption;
    }

    @Override
    public void createUser(User user) {
        user.setPassword(encryption.generate(user.getPassword()));

        if(userMapper.isExistAccount(user.getAccount()))
            throw new UserAlreadyExistException("Inputted Account is already exist.");
        if(userMapper.isExistNickname(user.getNickname()))
            throw new UserAlreadyExistException("Inputted Nickname is already exist.");
        userMapper.createUser(user);
    }
    @Override
    public UserRepresentInfoDTO getUserById(long id) {
        if(!userMapper.isExistUser(id))
            throw new UsernameNotFoundException("Inputted Path's User is not exists.");
        return userMapper.getUserRepresentInfoById(id);
    }
    @Override
    public void checkUserById(long id, String accessToken) {
        if(!userMapper.isExistUser(id))
            throw new UsernameNotFoundException("Inputted User is not exists.");
        if(id != jwtToken.verify(accessToken).getId())
            throw new UserMismatchException("Token's User is not equal to Inputted User.");
    }
    @Override
    public User updateUser(UpdateUserDTO updateUserDTO, String authHeader) {

        checkUserById(updateUserDTO.getId(), jwtToken.getAccessTokenInHeader(authHeader));

        if (!encryption.isMatch(updateUserDTO.getPassword(), userMapper.getUserById(updateUserDTO.getId()).getPassword()))
            throw new BadCredentialsException("Does Not Match the Existing Password.");
        if(!updateUserDTO.getUpdatedPassword().isEmpty())
            updateUserDTO.setPassword(encryption.generate(updateUserDTO.getUpdatedPassword()));
        userMapper.updateUser(updateUserDTO);

        return updateUserDTO;
    }
    @Override
    public void withdrawUser(long id, String authHeader) {

        checkUserById(id,jwtToken.getAccessTokenInHeader(authHeader));
        userMapper.withdrawUser(id);
    }

}
