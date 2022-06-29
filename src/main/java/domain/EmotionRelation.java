package domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmotionRelation {
    @NotEmpty(message = "EmotionWithUserDTO, item id is empty.")
    private long itemId;
    @NotEmpty(message = "EmotionWithUserDTO, emotion id is empty.")
    private long emotionId;
    private long userId;
}
