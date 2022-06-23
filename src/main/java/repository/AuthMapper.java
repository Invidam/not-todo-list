package repository;

import DTO.IdAndTokenDTO;
import DTO.LoginUserDTO;
import domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AuthMapper {
    Long getIdByRefreshToken(String refreshToken);
    String getRefreshTokenById(Long id);
    void setRefreshTokenById(Long id);
    void updateRefreshTokenById(IdAndTokenDTO idAndTokenDTO);
}
