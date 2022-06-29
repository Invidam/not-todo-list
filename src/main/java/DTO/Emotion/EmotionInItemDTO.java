package DTO.Emotion;


import DTO.User.UserInfoDTO;

import java.util.List;

public class EmotionInItemDTO {

    private long emotionId;
    private List<UserInfoDTO> userInfoList;

    public long getEmotionId() {
        return emotionId;
    }

    public List<UserInfoDTO> getUserInfoList() {
        return userInfoList;
    }

    public void setEmotionId(long emotionId) {
        this.emotionId = emotionId;
    }

    public void setUserInfoList(List<UserInfoDTO> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
