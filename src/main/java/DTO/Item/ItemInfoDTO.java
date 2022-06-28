package DTO.Item;

import DTO.Emotion.EmotionInItemDTO;
import DTO.User.UserInfoDTO;

import java.util.List;

public class ItemInfoDTO extends domain.Item{
    private UserInfoDTO owner;
    private List<EmotionInItemDTO> itemEmotionList;
    private List<String> hashTagList;


    public UserInfoDTO getOwner() {
        return owner;
    }

    public List<EmotionInItemDTO> getItemEmotionList() {
        return itemEmotionList;
    }

    public List<String> getHashTagList() {
        return hashTagList;
    }
    public void setOwner(UserInfoDTO owner) {
        this.owner = owner;
    }

    public void setItemEmotionList(List<EmotionInItemDTO> itemEmotionList) {
        this.itemEmotionList = itemEmotionList;
    }

    public void setHashTagList(List<String> hashTagList) {
        this.hashTagList = hashTagList;
    }

}

// read 완성하기
// emotion 개수들의 르스트