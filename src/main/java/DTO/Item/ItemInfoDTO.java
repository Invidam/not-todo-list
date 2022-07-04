package DTO.Item;

import DTO.Emotion.EmotionInItemDTO;
import DTO.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDTO extends DTO.Item.ItemDTO{
    private UserDTO owner;
    private List<EmotionInItemDTO> itemEmotionList;
    private List<String> hashTagList;

}