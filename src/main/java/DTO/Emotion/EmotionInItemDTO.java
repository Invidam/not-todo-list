package DTO.Emotion;


import DTO.User.UserInfoDTO;

import java.util.List;

public class EmotionInItemDTO {

    private long emotionId;
    private List<UserInfoDTO> userInfoDTOList;

    public long getEmotionId() {
        return emotionId;
    }

    public List<UserInfoDTO> getUserInfoDTOList() {
        return userInfoDTOList;
    }

    public void setEmotionId(long emotionId) {
        this.emotionId = emotionId;
    }

    public void setUserInfoDTOList(List<UserInfoDTO> userInfoDTOList) {
        this.userInfoDTOList = userInfoDTOList;
    }
}
