package org.bsm.pageModel;

public class AuthResult {
	private String Token;
	
	private String refreshToken;
	
	private Integer role;
	
	private String userName;

	public AuthResult(String token, String refreshToken,Integer role,String userName) {
		super();
		this.Token = token;
		this.refreshToken = refreshToken;
		this.role = role;
		this.userName = userName;
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
}
