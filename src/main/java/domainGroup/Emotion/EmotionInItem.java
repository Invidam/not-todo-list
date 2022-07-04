package domainGroup.Emotion;


import domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmotionInItem {

    private long emotionId;
    private List<User> userInfoList;

}