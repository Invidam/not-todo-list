package repository;

import DTO.Emotion.EmotionWithUserDTODTO;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionMapper {
    void createEmotion(EmotionWithUserDTODTO emotionWithUserDTO);
    void deleteEmotion(EmotionWithUserDTODTO emotionWithUserDTO);
}
