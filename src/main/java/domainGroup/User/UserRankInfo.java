package domainGroup.User;

import domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRankInfo extends User {
    private long point;
    private long ranking;
}
