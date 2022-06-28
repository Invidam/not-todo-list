package DTO.User;

import domain.Item;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UserRepresentInfoDTO extends UserInfoDTO {

    private List<Item> itemList;
    private Date createdAt;
}
