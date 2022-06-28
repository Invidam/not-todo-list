package DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SuccessInfo {
    private String type;
    private String message;
    private Date timestamp;

    public SuccessInfo(String type, String message) {
        this.type = type;
        this.message = message;
        this.timestamp = new Date();
    }
}
