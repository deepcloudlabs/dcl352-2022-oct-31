package com.example.cm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cm.application.CustomerApplication;
import com.example.cm.application.business.StandardCustomerApplication;
import com.example.cm.domain.event.CustomerEvent;
import com.example.cm.infrastructure.EventPublisher;
import com.example.cm.repository.CustomerRepository;

@Configuration
public class ApplicationConfig {

	@Bean
	CustomerApplication createCustomerApplication(CustomerRepository customerRepository,
			EventPublisher<CustomerEvent> eventPublisher) {
		return new StandardCustomerApplication(customerRepository, eventPublisher);
	}
}
