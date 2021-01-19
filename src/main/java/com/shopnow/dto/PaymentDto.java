package com.shopnow.dto;


public class PaymentDto {
	
    private int paymentid;
	private int model;
	private int orderid;
	private String fullname;
	private String email;
	private String address;
	private String city;
	private int total;
	private String itemname;
	private String modeofpayment;
	private int id;
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getModeofpayment() {
		return modeofpayment;
	}
	public void setModeofpayment(String modeofpayment) {
		this.modeofpayment = modeofpayment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PaymentDto(int model, int orderid, String fullname, String email, String address, String city, int total,
			String itemname, String modeofpayment, int id) {
		super();
		this.model = model;
		this.orderid = orderid;
		this.fullname = fullname;
		this.email = email;
		this.address = address;
		this.city = city;
		this.total = total;
		this.itemname = itemname;
		this.modeofpayment = modeofpayment;
		this.id = id;
	}
	public PaymentDto() {
		super();
	}
	@Override
	public String toString() {
		return "PaymentDto [paymentid=" + paymentid + ", model=" + model + ", orderid=" + orderid + ", fullname="
				+ fullname + ", email=" + email + ", address=" + address + ", city=" + city + ", total=" + total
				+ ", itemname=" + itemname + ", modeofpayment=" + modeofpayment + ", id=" + id + "]";
	}
	
	

}
