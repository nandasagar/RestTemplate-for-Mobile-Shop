package com.shopnow.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopnow.model.User;

public class UserHelper implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = -7349886175026884202L;
	@JsonProperty("User")
	public List<User>user;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
}
