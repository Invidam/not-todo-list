package domainGroup.User;

import domain.Item;
import domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRepresentInfo {
    User user;
    private List<Item> itemList;

}
