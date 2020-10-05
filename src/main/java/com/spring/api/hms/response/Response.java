package com.spring.api.hms.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Response<T> {
	private String status;
	private String message;
	private T data;

	public Response(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
}
