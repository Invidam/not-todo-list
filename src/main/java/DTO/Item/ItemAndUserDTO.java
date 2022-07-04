package DTO.Item;

import DTO.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAndUserDTO {
    private ItemDTO item;
    private UserDTO owner;
}
