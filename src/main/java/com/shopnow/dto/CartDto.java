package com.shopnow.dto;

public class CartDto {

	private int cartid;
	private String email;
	private int model;
	private int quantity;
	private int price;	
	private int total;
	private String itemname;
	private int id;
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CartDto(String email, int model, int quantity, int price, int total, String itemname, int id) {
		super();
		this.email = email;
		this.model = model;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.itemname = itemname;
		this.id = id;
	}
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartDto [cartid=" + cartid + ", email=" + email + ", model=" + model + ", quantity=" + quantity
				+ ", price=" + price + ", total=" + total + ", itemname=" + itemname + ", id=" + id + "]";
	}
	
	
}
