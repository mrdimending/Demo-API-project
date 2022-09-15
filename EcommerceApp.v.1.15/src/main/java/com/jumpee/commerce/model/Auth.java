package com.jumpee.commerce.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Auth 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(name="login_token")
	private String authz;
	
	@Column(name="reg_token")
	private String token;
	
	@Column(name="wallet_token")
	private String wToken;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public Auth()
	{
		
	}
	
	public Auth(int id, String auth, String verify, String wallet, User user) {
		super();
		this.id = id;
		this.authz = auth;
		this.token = verify;
		this.user = user;
		this.wToken = wallet;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthz() {
		return authz;
	}

	public void setAuthz(String authz) {
		this.authz = authz;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getwToken() {
		return wToken;
	}

	public void setwToken(String wToken) {
		this.wToken = wToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
}	