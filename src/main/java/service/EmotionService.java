package service;


import DTO.Emotion.EmotionWithUserDTODTO;

public interface EmotionService {
    void createEmotion(EmotionWithUserDTODTO emotionWithUserDTO);
    void deleteEmotion(EmotionWithUserDTODTO emotionWithUserDTO);
}

//이모션 생성 삭제
//이모션 전부 가져오기도 여기 해당할 수도 있음