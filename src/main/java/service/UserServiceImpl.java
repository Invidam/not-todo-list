package service;

import auth.JWTTOKEN;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    private final JWTTOKEN jwttoken;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(JWTTOKEN jwttoken) {
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
    @Override
    public String login(User user) {
        System.out.println(user.getAccount() +  user.getPassword());
        User loggedInUser = userMapper.getUserInLogin(user);
        System.out.println(loggedInUser.getId());
        return jwttoken.create(loggedInUser);
    }

    @Override
    public User verify(String token) {
        return jwttoken.verify(token);
    }
    @Override
    public User logout(String token) {
        return jwttoken.verify(token);
    }
}