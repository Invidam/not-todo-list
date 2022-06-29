package repository;

import DTO.User.RankDTO;
import DTO.User.UserRepresentInfoDTO;
import domain.User;
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
    UserRepresentInfoDTO getUserRepresentInfoById(long id);

    RankDTO getRank();
}
