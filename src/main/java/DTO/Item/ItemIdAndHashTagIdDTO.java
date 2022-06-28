package DTO.Item;

public class ItemIdAndHashTagIdDTO {
    private long itemId;
    private long hashTagId;

    public long getItemId() {
        return itemId;
    }

    public long getHashTag() {
        return hashTagId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public void setHashTag(long hashTagId) {
        this.hashTagId = hashTagId;
    }

    public ItemIdAndHashTagIdDTO(long itemId, long hashTagId) {
        this.itemId = itemId;
        this.hashTagId = hashTagId;
    }
    public ItemIdAndHashTagIdDTO() {}

}
