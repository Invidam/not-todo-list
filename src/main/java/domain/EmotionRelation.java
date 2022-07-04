package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmotionRelation {
    @NotEmpty(message = "EmotionRelation, item id is empty.")
    private long itemId;
    @NotEmpty(message = "EmotionRelation, emotion id is empty.")
    private long emotionId;
    private long userId;
}


