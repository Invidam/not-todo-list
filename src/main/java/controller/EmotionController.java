package controller;


import DTO.Emotion.EmotionWithUserDTO;
import DTO.Response.SuccessInfo;
import DTO.User.UserInfoDTO;
import auth.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmotionService;

@RestController
@RequestMapping("/emotion")
public class EmotionController {

    private final EmotionService emotionService;

    @Autowired
    private final JwtToken jwtToken;

    @Autowired
    public EmotionController(EmotionService emotionService, JwtToken jwtToken) {
        this.emotionService = emotionService;
        this.jwtToken = jwtToken;
    }
    void enrichEmotionWithUserDTO(EmotionWithUserDTO emotionWithUserDTO, String authHeader) {
        UserInfoDTO userInfoDTO = jwtToken.verify(jwtToken.getAccessTokenInHeader(authHeader));
        emotionWithUserDTO.setId(userInfoDTO.getId());
        emotionWithUserDTO.setNickname(userInfoDTO.getNickname());
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<SuccessInfo> createEmotion(@RequestBody EmotionWithUserDTO emotionWithUserDTO, @RequestHeader(value = "Authorization") String authHeader) {
        enrichEmotionWithUserDTO(emotionWithUserDTO,authHeader);

        emotionService.createEmotion(emotionWithUserDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessInfo("CREATE_EMOTION_RELATION", "success at create  emotion relation."));
    }

    @RequestMapping(value="", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessInfo> deleteEmotion(@RequestBody EmotionWithUserDTO emotionWithUserDTO, @RequestHeader(value = "Authorization") String authHeader) {
        enrichEmotionWithUserDTO(emotionWithUserDTO,authHeader);
        emotionService.deleteEmotion(emotionWithUserDTO);
        return ResponseEntity.ok(new SuccessInfo("DELETE_SUCCESS", "success at delete emotion relation."));
    }
}
