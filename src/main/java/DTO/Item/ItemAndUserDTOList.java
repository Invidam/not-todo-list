package DTO.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItemAndUserDTOList {
    private List<ItemAndUserDTO> items;
}
