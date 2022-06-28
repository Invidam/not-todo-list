package domain;

import java.sql.Date;

public class Item {
    private long id;
    private long userId;
    private String title;
    private boolean isDone;

    private int priority;

    private Date createdAt;
    private Date editedAt;
    private Date deadline;

    private String description;

    private boolean isEdited;
    private boolean isShared;
    private boolean isDeleted;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getPriority() {
        return priority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public boolean isShared() {
        return isShared;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
