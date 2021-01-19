package com.shopnow.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")

public class Orders implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6141474308773085693L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderid", unique = true, nullable = false)
	private int orderid;
	
	private List<Orders>orders;
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	@Column(name="email")
	private String email;
	

	
	@Column(name="model")
	private int model;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="total")
	private int total;

	@Column(name="itemname")
	private String itemname;

	 
	@Column(name="address")
	private String address;
	
	@Column(name="id")
	private int id;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	

	public Orders(int id, String email, int model, String  itemname, int quantity, int total,String address) {
		super();
		this.id=id;
		this.email = email;
		this.model = model;
		this.quantity = quantity;
		this.total = total;
		this.itemname = itemname;
		this.address=address;
	}
	public Orders() {
		super();
	}
	@Override
	public String toString() {
		return "Orders [orderid=" + orderid +",id="+id + ", email=" + email + ", address=" + address + ", model=" + model
				+ ", quantity=" + quantity + ", total=" + total + ", itemname=" + itemname + "]";
	}

	
		
}
