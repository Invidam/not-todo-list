package DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RankDTO {
    private List<UserRankInfoDTO> userRankList;
}
