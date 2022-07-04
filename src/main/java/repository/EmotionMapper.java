package repository;

import domain.EmotionRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionMapper {
    boolean isExistEmotionRelation(EmotionRelation emotionRelation);
    void createEmotion(EmotionRelation emotionRelation);
    void deleteEmotion(EmotionRelation emotionRelation);
}
