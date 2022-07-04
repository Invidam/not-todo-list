package DTO.Item;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInUserDTO {
    private long id;
    private String title;
    private boolean isDone;
    private int priority;
    private Date createdAt;
}
