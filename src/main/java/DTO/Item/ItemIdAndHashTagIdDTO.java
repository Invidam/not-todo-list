package DTO.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemIdAndHashTagIdDTO {
    private long itemId;
    private long hashTagId;

}
