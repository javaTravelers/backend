package br.com.javatravelers.JavaTravelers.domain.model.dto;

public class Credencial {
	private String login;
	private String token;
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getLogin() {
		return login;
	}
    public void setLogin(String login) {
		this.login = login;
	}
    
}