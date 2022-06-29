package DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRankInfoDTO extends UserInfoDTO {
    private long point;
    private long ranking;
}
