package com.shopnow.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment")

public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="paymentid")
	private int paymentid;
	
	@Column(name="model")
	private int model;
	
	@Column(name="orderid")
	private int orderid;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	
	@Column(name="total")
	private int total;
	
	@Column(name="itemname")
	private String itemname;

	
	@Column(name="modeofpayment")
	private String modeofpayment;

	@Column(name="id")
	private int id;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	


	public Payment(String fullname, String address, String city, String modeofpayment) {
		super();
		this.fullname = fullname;
		this.address = address;
		this.city = city;
		this.modeofpayment = modeofpayment;
	}
	public Payment(int id ,int model, int orderid, String fullname, String email, String address, String city, int total,
			String itemname, String modeofpayment) {
		super();
		this.id=id;
		this.model = model;
		this.orderid = orderid;
		this.fullname = fullname;
		this.email = email;
		this.address = address;
		this.city = city;
		this.total = total;
		this.itemname = itemname;
		this.modeofpayment = modeofpayment;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
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

	public String getModeofpayment() {
		return modeofpayment;
	}

	public void setModeofpayment(String modeofpayment) {
		this.modeofpayment = modeofpayment;
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

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "Payment [paymentid=" + paymentid + ",id="+ id +  ", fullname=" + fullname + ", email="
				+ email + ", address=" + address + ", city=" + city + ", total=" + total + ", itemname=" + itemname
				+ ", modeofpayment=" + modeofpayment + "]";
	}




	

}
