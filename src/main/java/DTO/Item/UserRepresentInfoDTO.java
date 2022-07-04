package DTO.Item;

import DTO.User.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRepresentInfoDTO {
    UserDTO user;
    private List<ItemInUserDTO> itemList;

}
