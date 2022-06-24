package domain;

import java.sql.Date;

public class User {

    private Long id;
    private String account;
    private String password;
    private String nickname;
    private Date createdAt;
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
    public Date getCreatedAt() { return createdAt; }
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
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}

}
