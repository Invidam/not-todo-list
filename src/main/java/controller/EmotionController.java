package controller;


import DTO.Emotion.EmotionWithUserDTODTO;
import DTO.User.UserInfoDTO;
import auth.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.EmotionService;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value="", method = RequestMethod.POST)
    public void createEmotion(@RequestBody EmotionWithUserDTODTO emotionWithUserDTO, HttpServletRequest httpServletRequest) {
        UserInfoDTO userInfoDTO = jwtToken.verify(httpServletRequest.getHeader("Authorization").substring(7));
        emotionWithUserDTO.setId(userInfoDTO.getId());
        emotionWithUserDTO.setNickname(userInfoDTO.getNickname());

        emotionService.createEmotion(emotionWithUserDTO);
    }

    @RequestMapping(value="", method = RequestMethod.DELETE)
    public void deleteEmotion(@RequestBody EmotionWithUserDTODTO emotionWithUserDTO, HttpServletRequest httpServletRequest) {
        UserInfoDTO userInfoDTO = jwtToken.verify(httpServletRequest.getHeader("Authorization").substring(7));
        emotionWithUserDTO.setId(userInfoDTO.getId());
        emotionWithUserDTO.setNickname(userInfoDTO.getNickname());

        emotionService.deleteEmotion(emotionWithUserDTO);

    }
}
