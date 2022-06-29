package service;


import DTO.Emotion.EmotionWithUserDTO;

public interface EmotionService {
    void createEmotion(EmotionWithUserDTO emotionWithUserDTO);
    void deleteEmotion(EmotionWithUserDTO emotionWithUserDTO);
}

//이모션 생성 삭제
//이모션 전부 가져오기도 여기 해당할 수도 있음