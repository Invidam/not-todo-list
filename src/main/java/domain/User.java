package domain;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String account;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    private boolean isDeleted;
    private String refreshToken;


    public Long getId() {
        return this.id;
    }
    public String getAccount() {
        return this.account;
    }
    public String getPassword() {
        return this.password;
    }
    public String getNickname() {
        return this.nickname;
    }
    public boolean getIsDeleted() {
        return this.isDeleted;
    }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getRefreshToken() {return refreshToken;}


    public void setId(Long id) {
        this.id = id;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}

}
