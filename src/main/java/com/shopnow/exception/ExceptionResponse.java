package com.shopnow.exception;

import java.util.Date;

public class ExceptionResponse {
	Date date; String message; String description;

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public ExceptionResponse(Date date, String message, String description) {
		super();
		this.date = date;
		this.message = message;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [date=" + date + ", message=" + message + ", description=" + description + "]";
	}


	
	
}
