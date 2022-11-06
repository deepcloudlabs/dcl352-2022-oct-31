package com.example.cm.domain.event;

import com.example.cm.domain.Customer;

public class CustomerReleasedEvent extends CustomerEvent {
	private final Customer customer;

	public CustomerReleasedEvent(Customer customer) {
		super(customer.getEmail());
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

}
