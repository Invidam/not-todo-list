package DTO.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemIdAndHashTagDTO {
    private long id;
    private String hashTag;
}
