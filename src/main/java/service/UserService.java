package service;

import DTO.User.RankDTO;
import DTO.User.UpdateUserDTO;
import DTO.User.UserRepresentInfoDTO;
import domain.User;

public interface UserService {
    void createUser(User user);
    UserRepresentInfoDTO getUserById(long id);

    void checkUserById(long id, String accessToken);

    void updateUser(UpdateUserDTO updateUserDTO, String authHeader);

    void withdrawUser(long id, String authHeader);

    RankDTO getRank();
}