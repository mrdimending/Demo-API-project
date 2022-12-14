package com.jumpee.commerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.view.View;

@Entity
@Table(name = "wallet")
public class Wallet 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@JsonView(View.Base.class)
	@Range(min = 100, message = "minimum deposit must be 100")
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Wallet()
	{}
	public Wallet(int id, BigDecimal amount, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
