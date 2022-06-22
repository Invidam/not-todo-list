package service;

import domain.User;

public interface UserService {
    void createUser(User user);
    User getUserById(long id);
    void updateUser(User user);
    void withdrawUser(long id);

    String login(User user);
    User verify(String token);
    User logout(String token);
}