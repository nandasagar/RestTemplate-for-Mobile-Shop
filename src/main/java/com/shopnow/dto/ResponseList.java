package com.shopnow.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.shopnow.model.Cart;
import com.shopnow.model.Item;
import com.shopnow.model.Orders;
import com.shopnow.model.Payment;
import com.shopnow.model.User;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonSubTypes({@Type(User.class)}) 
public class ResponseList implements Serializable {

	/**
	 * 
	 */
	private UserHelper User;
	 public List<User> items;
	 
	
	 public List<User> getItems() {
		return items;
	}

	public void setItems(List<User> items) {
		this.items = items;
	}

	@JsonProperty("ResponseList")
	  UserHelper getStudents() {
	    return User;
	  } 

	public void setUser(UserHelper user) {
		User = user;
	}

	private static final long serialVersionUID = -5559958077583417588L;
	
	/*
	private List<Item>item;
	
	private List<Orders>orders;
	
	private List<Cart>cart;
	
	private List<Payment>payment;
	*/
	
	/*
	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public ResponseList() {
		super();
		// TODO Auto-generated constructor stub
	}

*/
	
	
}
