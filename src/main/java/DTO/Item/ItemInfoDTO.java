package DTO.Item;

import DTO.Emotion.EmotionInItemDTO;
import DTO.User.UserInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemInfoDTO extends domain.Item{
    private UserInfoDTO owner;
    private List<EmotionInItemDTO> itemEmotionList;
    private List<String> hashTagList;

}

// read 완성하기
// emotion 개수들의 르스트