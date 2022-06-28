package DTO.Item;

public class ItemIdAndHashTagDTO {
    private long id;
    private String hashTag;

    public ItemIdAndHashTagDTO(long id, String hashTag) {
        this.id = id;
        this.hashTag = hashTag;
    }

    public long getId() {
        return id;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
}
