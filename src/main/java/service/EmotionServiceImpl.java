package service;

import domain.EmotionRelation;
import enums.ExceptionMessage;
import exception.emotion.AlreadyExistEmotionRelationException;
import exception.emotion.EmotionRelationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmotionMapper;

@Service
public class EmotionServiceImpl implements EmotionService {

    private final EmotionMapper emotionMapper;

    @Autowired
    public EmotionServiceImpl(EmotionMapper emotionMapper ) {
        this.emotionMapper = emotionMapper;
    }

    @Override
    public void createEmotion(EmotionRelation emotionRelation) {
        if(emotionMapper.isExistEmotionRelation(emotionRelation))
            throw new AlreadyExistEmotionRelationException(ExceptionMessage.EMOTION_REL_IS_EXIST.getMessage());
        emotionMapper.createEmotion(emotionRelation);
    }

    @Override
    public void deleteEmotion(EmotionRelation emotionRelation) {
        if(!emotionMapper.isExistEmotionRelation(emotionRelation))
            throw new EmotionRelationNotFoundException(ExceptionMessage.EMOTION_REL_NOT_FOUND.getMessage());
        emotionMapper.deleteEmotion(emotionRelation);
    }

}