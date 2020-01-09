package org.bsm.pageModel;

public class AuthResult {
	private String Token;
	
	private String refreshToken;

	public AuthResult(String token, String refreshToken) {
		super();
		Token = token;
		this.refreshToken = refreshToken;
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
}
