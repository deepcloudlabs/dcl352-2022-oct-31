package com.example.cm.application.business;

import java.util.Optional;
import java.util.function.Consumer;

import com.example.cm.application.CustomerApplication;
import com.example.cm.domain.Application;
import com.example.cm.domain.Customer;
import com.example.cm.domain.Email;
import com.example.cm.domain.event.CustomerAcquiredEvent;
import com.example.cm.domain.event.CustomerEvent;
import com.example.cm.domain.event.CustomerReleasedEvent;
import com.example.cm.infrastructure.EventPublisher;
import com.example.cm.repository.CustomerRepository;

@Application(contract = CustomerApplication.class)
public class StandardCustomerApplication implements CustomerApplication {
	private final CustomerRepository customerRepository;
	private final EventPublisher<CustomerEvent> eventPublisher;

	public StandardCustomerApplication(CustomerRepository customerRepository,
			EventPublisher<CustomerEvent> eventPublisher) {
		this.customerRepository = customerRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Customer acquireCustomer(Customer customer) {
		var email = customer.getEmail();
		if (customerRepository.exists(email))
			throw new IllegalArgumentException("Customer already exists");
		Customer acquiredCustomer = customerRepository.create(customer);
		var event = new CustomerAcquiredEvent(email);
		eventPublisher.publishEvent(event);
		return acquiredCustomer;
	}

	@Override
	public Optional<Customer> releaseCustomer(Email email) {
		var customer = customerRepository.findCustomerByEmail(email);
		Consumer<Customer> removeCustomer = customerRepository::removeCustomer;
		Consumer<Customer> publishEvent = cust -> {
			eventPublisher.publishEvent(new CustomerReleasedEvent(cust));			
		};
		customer.ifPresent(removeCustomer.andThen(publishEvent));
		return customer;
	}

	@Override
	public Optional<Customer> retrieveCustomer(Email email) {
		return customerRepository.findCustomerByEmail(email);
	}

}
