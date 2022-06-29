package repository;

import DTO.Emotion.EmotionWithUserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionMapper {
    boolean isExistEmotionRelation(EmotionWithUserDTO emotionWithUserDTO);
    void createEmotion(EmotionWithUserDTO emotionWithUserDTO);
    void deleteEmotion(EmotionWithUserDTO emotionWithUserDTO);
}
