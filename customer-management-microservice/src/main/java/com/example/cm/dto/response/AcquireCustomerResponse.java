package com.example.cm.dto.response;

public class AcquireCustomerResponse {
	private final String status;

	public AcquireCustomerResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
