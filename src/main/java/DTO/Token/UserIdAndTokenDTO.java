package DTO.Token;

public class UserIdAndTokenDTO {
    private long id;
    private String refreshToken;

    public UserIdAndTokenDTO(long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

    public long getId() {
        return id;
    }
    public String getRefreshToken() {
        return refreshToken;
    }



    public void setId(long id) {
        this.id = id;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
