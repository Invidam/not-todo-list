package DTO;

public class IdAndTokenDTO {
    private Long id;
    private String refreshToken;

    public IdAndTokenDTO(Long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }
    public String getRefreshToken() {
        return refreshToken;
    }



    public void setId(Long id) {
        this.id = id;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
