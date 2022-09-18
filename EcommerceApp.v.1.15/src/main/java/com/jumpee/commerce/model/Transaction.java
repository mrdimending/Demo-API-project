package com.jumpee.commerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.view.View;

@Entity
@Table(name = "transaction")
public class Transaction 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	private String category;
	
	@JsonView(View.Base.class)
	private String activity;
	
	@JsonView(View.Base.class)
	private String status;
	
	@JsonView(View.Base.class)
	private String atDateAndTime;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Transaction() {}
	public Transaction(int id, String category, String activity, String status, String atDateAndTime, User user) {
		super();
		this.id = id;
		this.category = category;
		this.activity = activity;
		this.status = status;
		this.atDateAndTime = atDateAndTime;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAtDateAndTime() {
		return atDateAndTime;
	}
	public void setAtDateAndTime(String atDateAndTime) {
		this.atDateAndTime = atDateAndTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
