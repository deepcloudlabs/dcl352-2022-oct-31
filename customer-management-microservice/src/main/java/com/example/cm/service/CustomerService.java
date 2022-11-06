package com.example.cm.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cm.application.CustomerApplication;
import com.example.cm.domain.Customer;
import com.example.cm.domain.Email;
import com.example.cm.dto.request.AcquireCustomerRequest;
import com.example.cm.dto.response.AcquireCustomerResponse;
import com.example.cm.dto.response.CustomerResponse;

@Service
public class CustomerService {
    private final CustomerApplication customerApplication;
    private final ModelMapper modelMapper;
    
	public CustomerService(CustomerApplication customerApplication, ModelMapper modelMapper) {
		this.customerApplication = customerApplication;
		this.modelMapper = modelMapper;
	}

	public CustomerResponse getCustomerByEmail(String email) {
		var customer = customerApplication.retrieveCustomer(Email.valueOf(email))
				                  .orElseThrow(() -> new IllegalArgumentException("Cannot find customer"));
		return modelMapper.map(customer,CustomerResponse.class);
	}

	@Transactional
	public AcquireCustomerResponse acquireCustomer(AcquireCustomerRequest request) {
		var customer = modelMapper.map(request,Customer.class);
		customerApplication.acquireCustomer(customer);
		return new AcquireCustomerResponse("Ok");
	}

	@Transactional
	public CustomerResponse releaseCustomer(String email) {
		var releasedCustomer = customerApplication.releaseCustomer(Email.valueOf(email))
				                                  .orElseThrow(() -> new IllegalArgumentException("Cannot find customer"));
		return modelMapper.map(releasedCustomer,CustomerResponse.class);
	}

}
