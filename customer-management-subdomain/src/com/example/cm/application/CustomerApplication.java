package com.example.cm.application;

import java.util.Optional;

import com.example.cm.domain.Application;
import com.example.cm.domain.Customer;
import com.example.cm.domain.Email;

@Application
public interface CustomerApplication {
	public Customer acquireCustomer(Customer customer);
	public Optional<Customer> releaseCustomer(Email email);
	public Optional<Customer> retrieveCustomer(Email email);
}
