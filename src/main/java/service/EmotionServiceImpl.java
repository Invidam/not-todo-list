package service;

import DTO.Emotion.EmotionWithUserDTO;
import exception.emotion.AlreadyExistEmotionRelationException;
import exception.emotion.EmotionRelationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmotionMapper;

@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Override
    public void createEmotion(EmotionWithUserDTO emotionWithUserDTO) {
        if(emotionMapper.isExistEmotionRelation(emotionWithUserDTO))
            throw new AlreadyExistEmotionRelationException("Emotion Relation is already exist.");
        emotionMapper.createEmotion(emotionWithUserDTO);
    }

    @Override
    public void deleteEmotion(EmotionWithUserDTO emotionWithUserDTO) {
        if(!emotionMapper.isExistEmotionRelation(emotionWithUserDTO))
            throw new EmotionRelationNotFoundException("Emotion Relation is not found.");
            //아이템 존재 판단?
            //감정표현 존재 판단? - 프론트엔드에 감정표현 목록이 이미 존재함 (선택하기 위해)
        emotionMapper.deleteEmotion(emotionWithUserDTO);
    }

}