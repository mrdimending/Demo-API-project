package com.jumpee.commerce.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.view.View;

@Entity
@Table(name="orders")
public class Order 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@JsonView(View.Base.class)
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<Cart> cart;
	
	@JsonView(View.Base.class)
	@ManyToOne
    @JoinColumn(name = "details_id", referencedColumnName = "id")
	private Details details;
	
	@JsonView(View.Base.class)
	private BigDecimal amount;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Order()
	{}

	public Order(int id, List<Cart> cart, Details details, BigDecimal amount, User user) {
		super();
		this.id = id;
		this.cart = cart;
		this.details = details;
		this.amount = amount;
		this.user = user;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
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
