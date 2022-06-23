package service;

import DTO.LoginUserDTO;
import DTO.TokenDTO;
import auth.JwtToken;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    private final JwtToken jwttoken;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(JwtToken jwttoken) {
        this.jwttoken = jwttoken;
    }

    @Override
    public void createUser(User user) {
        userMapper.createUser(user);
    }
    @Override
    public User getUserById(long id) {
        User user = userMapper.getUserById(id);
        user.setId(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        try {
            userMapper.updateUser(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdrawUser(long id) {
        try {
            userMapper.withdrawUser(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}