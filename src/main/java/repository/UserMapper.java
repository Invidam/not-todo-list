package repository;

import domain.User;
import domainGroup.User.Rank;
import domainGroup.User.UserRepresentInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void createUser(User user);
    User getUserById(long id);
    void updateUser(User user);
    void withdrawUser(long id);
    User getUserByAccount(String account);
    boolean isExistAccount(String account);
    boolean isExistNickname(String account);
    boolean isExistUser(long id);
    UserRepresentInfo getUserRepresentInfoById(long id);

    Rank getRank();
}
