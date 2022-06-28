package domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Getter
@Setter
public class Item {
    private long id;
    @NotEmpty(message = "User ID is empty.")
    private long userId;
    @NotEmpty(message = "Title is empty.")
    private String title;
    private boolean isDone;

    @NotEmpty(message = "Priority is empty.")
    private int priority;

    @NotEmpty(message = "Created at is empty.")
    private Date createdAt;
    private Date editedAt;
    private Date deadline;

    @NotEmpty(message = "Description is empty.")
    private String description;

    private boolean isEdited;
    private boolean isShared;
    private boolean isDeleted;


    public boolean getIsDone() {
        return isDone;
    }

    public boolean getIsEdited() {
        return isEdited;
    }

    public boolean getIsShared() {
        return isShared;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public void setIsEdited(boolean edited) {
        isEdited = edited;
    }

    public void setIsShared(boolean shared) {
        isShared = shared;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
