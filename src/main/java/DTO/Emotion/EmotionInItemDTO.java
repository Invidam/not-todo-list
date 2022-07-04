package DTO.Emotion;


import DTO.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmotionInItemDTO {

    private long emotionId;
    private List<UserDTO> userInfoList;
}