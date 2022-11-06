package com.example.cm.config;

import java.time.Year;
import java.util.Base64;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cm.document.CustomerDocument;
import com.example.cm.domain.Customer;
import com.example.cm.domain.Phone;
import com.example.cm.dto.request.AcquireCustomerRequest;
import com.example.cm.dto.response.CustomerResponse;

@Configuration
public class ModelMapperConfig {
	private static final Converter<AcquireCustomerRequest, Customer> ACQUIRE_CUSTOMER_REQUEST_TO_CUSTOMER_MAPPER = context -> {
		var request = context.getSource();
		return new Customer.Builder().email(request.getEmail()).birthYear(Year.now().getValue() - request.getAge())
				.fullName(request.getFirstName(), request.getLastName()).photo(request.getPhoto())
				.phones(request.getPhones()).customerType(request.getType()).build();
	};

	private static final Converter<CustomerDocument, Customer> CUSTOMER_DOCUMENT_TO_CUSTOMER_MAPPER = context -> {
		var document = context.getSource();
		return new Customer.Builder().email(document.getEmail()).birthYear(2022 - document.getAge())
				.fullName(document.getFirstName(), document.getLastName()).photo(document.getPhoto())
				.phones(document.getPhones()).customerType(document.getType()).build();
	};

	private static final Converter<Customer, CustomerResponse> CUSTOMER_TO_CUSTOMER_RESPONSE_MAPPER = context -> {
		var customer = context.getSource();
		var customerResponse = new CustomerResponse();
		customerResponse.setEmail(customer.getEmail().getValue());
		customerResponse.setFirstName(customer.getFullName().firstName());
		customerResponse.setLastName(customer.getFullName().lastName());
		customerResponse.setType(customer.getCustomerType());
		customerResponse.setAge(Year.now().getValue() - customer.getBirthYear().value());
		customerResponse.setPhoto(Base64.getEncoder().encodeToString(customer.getPhoto().getValues()));
		customerResponse.setPhones(customer.getPhones().stream().map(Phone::value).toList());
		return customerResponse;
	};

	private static final Converter<Customer, CustomerDocument> CUSTOMER_TO_CUSTOMER_DOCUMENT_MAPPER = context -> {
		var customer = context.getSource();
		var document = new CustomerDocument();
		document.setEmail(customer.getEmail().getValue());
		document.setFirstName(customer.getFullName().firstName());
		document.setLastName(customer.getFullName().lastName());
		document.setType(customer.getCustomerType());
		document.setAge(Year.now().getValue() - customer.getBirthYear().value());
		document.setPhoto(Base64.getEncoder().encodeToString(customer.getPhoto().getValues()));
		document.setPhones(customer.getPhones().stream().map(Phone::value).toList());
		return document;
	};

	@Bean
	ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(CUSTOMER_TO_CUSTOMER_RESPONSE_MAPPER, Customer.class, CustomerResponse.class);
		modelMapper.addConverter(ACQUIRE_CUSTOMER_REQUEST_TO_CUSTOMER_MAPPER, AcquireCustomerRequest.class,
				Customer.class);
		modelMapper.addConverter(CUSTOMER_TO_CUSTOMER_DOCUMENT_MAPPER, Customer.class, CustomerDocument.class);
		modelMapper.addConverter(CUSTOMER_DOCUMENT_TO_CUSTOMER_MAPPER, CustomerDocument.class, Customer.class);
		return modelMapper;
	}
}
