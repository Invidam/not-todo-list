package domainGroup.Item;

import domain.Item;
import domain.User;
import domainGroup.Emotion.EmotionInItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemInfo extends Item {
    private User owner;
    private List<EmotionInItem> itemEmotionList;
    private List<String> hashTagList;

}