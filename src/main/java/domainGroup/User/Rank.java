package domainGroup.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Rank {
    private List<UserRankInfo> userRankList;
}
