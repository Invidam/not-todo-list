package service;

import DTO.Emotion.EmotionWithUserDTODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmotionMapper;

@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Override
    public void createEmotion(EmotionWithUserDTODTO emotionWithUserDTO) {
        emotionMapper.createEmotion(emotionWithUserDTO);
    }

    @Override
    public void deleteEmotion(EmotionWithUserDTODTO emotionWithUserDTO) {
        emotionMapper.deleteEmotion(emotionWithUserDTO);
    }

}