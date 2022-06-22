package domain;

public class ItemEmotion {
    private Long itemId;
    private Long emotionId;
    private String emotionName;
    private Long count;

    public Long getItemId() {
        return itemId;
    }

    public Long getEmotionId() {
        return emotionId;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public Long getCount() {
        return count;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setEmotionId(Long emotionId) {
        this.emotionId = emotionId;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
