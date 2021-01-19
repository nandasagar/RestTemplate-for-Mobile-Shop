package com.shopnow.dto;



public class UserDto {

	private int id;
	
	private String email;
	
	private String userName;

	private Long phoneNumber;
	
	private String password;
	
	private String rolename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public UserDto(String email, String userName, Long phoneNumber, String password, String rolename) {
		super();
		this.email = email;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.rolename = rolename;
	}


	public UserDto(int id, String email, String userName, Long phoneNumber, String password, String rolename) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.rolename = rolename;
	}
	public UserDto() {
		super();
	}

	
	
}
