package service;

import domain.User;
import domainGroup.User.Rank;
import domainGroup.User.UserRepresentInfo;

public interface UserService {
    void checkExistInfo(User user);

    void createUser(User user);

    UserRepresentInfo getUserRepresentInfoById(long id);

    void checkUserById(long id, String accessToken);

    void updateUser(User user, String updatePassword);

    void withdrawUser(long id);

    Rank getRank();
}