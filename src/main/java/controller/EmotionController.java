package controller;


import DTO.Emotion.EmotionWithUserDTO;
import auth.JwtToken;
import enums.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmotionService;
import utility.DTOEntityMapper;

@RestController
@RequestMapping("/emotion")
public class EmotionController {

    private final EmotionService emotionService;
    private final JwtToken jwtToken;
    private final DTOEntityMapper dtoEntityMapper;

    @Autowired
    public EmotionController(EmotionService emotionService, JwtToken jwtToken, DTOEntityMapper dtoEntityMapper) {
        this.emotionService = emotionService;
        this.jwtToken = jwtToken;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    void enrichEmotionWithUserDTO(EmotionWithUserDTO emotionWithUserDTO, String authHeader) {
        long userId = jwtToken.verifyAccessToken(jwtToken.getAccessTokenInHeader(authHeader));
        emotionWithUserDTO.setUserId(userId);
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<SuccessStatus> createEmotion(@RequestBody EmotionWithUserDTO emotionWithUserDTO, @RequestHeader(value = "Authorization") String authHeader) {
        enrichEmotionWithUserDTO(emotionWithUserDTO,authHeader);
        emotionService.createEmotion(dtoEntityMapper.toEntity(emotionWithUserDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatus.CREATE_EMOTION_REL);
    }

    @RequestMapping(value="", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessStatus> deleteEmotion(@RequestBody EmotionWithUserDTO emotionWithUserDTO, @RequestHeader(value = "Authorization") String authHeader) {
        enrichEmotionWithUserDTO(emotionWithUserDTO,authHeader);
        emotionService.deleteEmotion(dtoEntityMapper.toEntity(emotionWithUserDTO));

        return ResponseEntity.ok(SuccessStatus.DELETE_EMOTION_REL);
    }
}
