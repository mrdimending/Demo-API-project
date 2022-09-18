package com.jumpee.commerce.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.view.View;

@Entity
@Table(name = "user")
public class User 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@JsonView(View.Base.class)
	@Column(name = "email")
	@Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Invalid email format")
	private String email;
	
	@Column(name = "password")
//	@Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "Password must be alphanumeric 8 - 16 characters")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	
	@JsonView(View.Base.class)
	@Column(name = "first_name")
	@Size(min = 2, message = "First name must be at least 2 characters")
	private String first_name;
	
	@JsonView(View.Base.class)
	@Column(name = "last_name")
	@Size(min = 2, message = "Last name must be at least 2 characters")
	private String last_name;
	
	@JsonView(View.Base.class)
	@Column(name = "contact_num")
	@Pattern(regexp = "^(09)\\d{9}$", message = "Invalid mobile number")
	private String contact_num;
	
	@JsonView(View.Base.class)
	@ManyToOne
	private Role roles;
	
	@JsonView(View.Base.class)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Details> userDetails;
	
	@JsonView(View.Base.class)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Collection<Wallet> wallet;
	
	@JsonView(View.Base.class)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Cart> cart;
	
	
	private boolean active = false;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getFirst_name() 
	{
		return first_name;
	}

	public void setFirst_name(String first_name) 
	{
		this.first_name = first_name;
	}

	public String getLast_name() 
	{
		return last_name;
	}

	public void setLast_name(String last_name) 
	{
		this.last_name = last_name;
	}

	public String getContact_num() 
	{
		return contact_num;
	}

	public void setContact_num(String contact_num) 
	{
		this.contact_num = contact_num;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}
	public Collection<Wallet> getWallet() {
		return wallet;
	}

	public void setWallet(Collection<Wallet> wallet) {
		this.wallet = wallet;
	}

	public List<Details> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<Details> userDetails) {
		this.userDetails = userDetails;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public User() {}
	public User(int id,
			@Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Invalid email format") String email,
			@Size(min = 8, message = "Password must be at least 8 characters") String password,
			@Size(min = 2, message = "First name must be at least 2 characters") String first_name,
			@Size(min = 2, message = "Last name must be at least 2 characters") String last_name,
			@Pattern(regexp = "^(09)\\d{9}$", message = "Invalid mobile number") String contact_num,
			Role roles, boolean active) {
		super();
		//this.id = id;
		this.email = email;
		//this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact_num = contact_num;
		this.roles = roles;
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", contact_num=" + contact_num + ", roles=" + roles
				+ ", active=" + active + "]";
	}

	
	
}