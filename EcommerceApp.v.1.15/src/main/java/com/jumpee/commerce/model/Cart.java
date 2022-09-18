package com.jumpee.commerce.model;

import java.math.BigDecimal;

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
@Table(name = "cart")
public class Cart 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;

	@JsonView(View.Base.class)
	@ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@JsonView(View.Base.class)
	private int qty;

	@JsonView(View.Base.class)
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Cart()
	{}
	public Cart(int id, User user, Product product, int qty) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
