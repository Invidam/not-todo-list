package service;


import domain.EmotionRelation;

public interface EmotionService {
    void createEmotion(EmotionRelation emotionRelation);
    void deleteEmotion(EmotionRelation emotionRelation);
}