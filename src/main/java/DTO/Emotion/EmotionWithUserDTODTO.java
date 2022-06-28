package DTO.Emotion;

import DTO.User.UserInfoDTO;

public class EmotionWithUserDTODTO extends UserInfoDTO {
    //UserInTokenDTO
    //이모션 id랑 게시글 id
    private long itemId;
    private long emotionId;

    public long getItemId() {
        return itemId;
    }

    public long getEmotionId() {
        return emotionId;
    }


    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setEmotionId(long emotionId) {
        this.emotionId = emotionId;
    }

}

//이걸 응용해서 유저정보도 가지고 카운트도 가지는 이모션 인포 만들어서 사용하기