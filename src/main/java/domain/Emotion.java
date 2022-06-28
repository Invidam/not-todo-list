package domain;

public class Emotion {
    private long itemId;
    private long emotionId;
    private String emotionName;

    public long getItemId() {
        return itemId;
    }

    public long getEmotionId() {
        return emotionId;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setEmotionId(long emotionId) {
        this.emotionId = emotionId;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

}
