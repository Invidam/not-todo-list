package DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ErrorInfo {
    private String type;
    private String message;
    private Date timestamp;
    private long status;
    private long code;

    public ErrorInfo(String type, String message, long status, long code) {
        this.type = type;
        this.message = message;
        this.status = status;
        this.code = code;
        this.timestamp = new Date();
    }
}
