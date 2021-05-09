package org.bsm.pageModel;

public class AuthResult {
    private String Token;

    private String refreshToken;

    private Integer role;

    private String userName;

    private String userlog;

    private Integer isFaceValid;

    public AuthResult(String token, String refreshToken, Integer role, String userName, String userlog, Integer isFaceValid) {
        super();
        this.Token = token;
        this.refreshToken = refreshToken;
        this.role = role;
        this.userName = userName;
        this.userlog = userlog;
        this.isFaceValid = isFaceValid;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserlog() {
        return userlog;
    }

    public void setUserlog(String userlog) {
        this.userlog = userlog;
    }

    public Integer getIsFaceValid() {
        return isFaceValid;
    }

    public void setIsFaceValid(Integer isFaceValid) {
        this.isFaceValid = isFaceValid;
    }

    @Override
    public String toString() {
        return "AuthResult [Token=" + Token + ", refreshToken=" + refreshToken + ", role=" + role + ", userName="
                + userName + ", userlog=" + userlog + ", isFaceValid=" + isFaceValid + "]";
    }


}
