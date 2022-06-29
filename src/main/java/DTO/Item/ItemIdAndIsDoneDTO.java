package DTO.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemIdAndIsDoneDTO {
    private long id;
    private boolean isDone;

    public boolean getIsDone() {return isDone;}
    public void setIsDone(boolean isDone) {this.isDone = isDone;}
}
