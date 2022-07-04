package repository;

import DTO.User.UserIdAndTokenDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {
    long getIdByRefreshToken(String refreshToken);
    String getRefreshTokenById(long id);
    void setRefreshTokenById(long id);
    void updateRefreshTokenById(UserIdAndTokenDTO userIdAndTokenDTO);
}
