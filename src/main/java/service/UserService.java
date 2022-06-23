package service;

import DTO.LoginUserDTO;
import DTO.TokenDTO;
import domain.User;

public interface UserService {
    void createUser(User user);
    User getUserById(long id);
    void updateUser(User user);
    void withdrawUser(long id);

}