package com.example.cm.repository;

import java.util.Optional;

import com.example.cm.domain.Customer;
import com.example.cm.domain.Email;
import com.example.cm.domain.Repository;

@Repository(entity=Customer.class)
public interface CustomerRepository {

	boolean exists(Email email);

	Customer create(Customer customer);

	Optional<Customer> findCustomerByEmail(Email email);

	void removeCustomer(Customer customer);

}
