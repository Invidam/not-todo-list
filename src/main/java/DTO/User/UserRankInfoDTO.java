package DTO.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRankInfoDTO extends UserDTO {
    private long point;
    private long ranking;

    public UserRankInfoDTO(long point, long ranking, long id, String nickname) {
        super(id,nickname);
        this.point = point;
        this.ranking = ranking;
    }
}
