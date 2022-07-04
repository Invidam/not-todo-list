package DTO.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private long id;

    @NotEmpty(message = "Item Title is empty.")
    private String title;

    private boolean isDone;

    @NotEmpty(message = "Priority is empty.")
    private int priority;

    private Date createdAt;
    private Date editedAt;
    private Date deadline;

    @NotEmpty(message = "Description is empty.")
    private String description;

    private boolean isEdited;
    private boolean isShared;

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public boolean getIsEdited() {
        return isEdited;
    }

    public void setIsEdited(boolean edited) {
        isEdited = edited;
    }

    public boolean getIsShared() {
        return isShared;
    }

    public void setIsShared(boolean shared) {
        isShared = shared;
    }
}
