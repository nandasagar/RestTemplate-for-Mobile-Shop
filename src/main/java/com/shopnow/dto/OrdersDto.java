package com.shopnow.dto;

import java.io.Serializable;

public class OrdersDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7666500240004342356L;

	private int orderid;
	
	private String email;
	
	private int model;

	private int quantity;

	private int total;

	private String itemname;

	private String address;

	private int id;

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

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdersDto(String email, int model, int quantity, int total, String itemname, String address, int id) {
		super();
		this.email = email;
		this.model = model;
		this.quantity = quantity;
		this.total = total;
		this.itemname = itemname;
		this.address = address;
		this.id = id;
	}

	public OrdersDto() {
		super();
	}

	@Override
	public String toString() {
		return "OrdersDto [orderid=" + orderid + ", email=" + email + ", model=" + model + ", quantity=" + quantity
				+ ", total=" + total + ", itemname=" + itemname + ", address=" + address + ", id=" + id + "]";
	}
	

	

}
