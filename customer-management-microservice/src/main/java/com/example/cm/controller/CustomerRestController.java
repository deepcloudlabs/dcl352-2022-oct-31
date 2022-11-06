package com.example.cm.controller;

import javax.validation.constraints.Email;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.cm.dto.request.AcquireCustomerRequest;
import com.example.cm.dto.response.AcquireCustomerResponse;
import com.example.cm.dto.response.CustomerResponse;
import com.example.cm.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequestScope
@CrossOrigin
@Validated
// Adapter: Http -> Class
public class CustomerRestController {
	private final CustomerService customerService;
	
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// GET http://localhost:7100/cm/api/v1/customers/jack@example.com
	@GetMapping("{email}")
	public CustomerResponse getCustomer(@PathVariable @Email String email) {
		return customerService.getCustomerByEmail(email);
	}
	
	// POST http://localhost:7100/cm/api/v1/customers
	@PostMapping
	public AcquireCustomerResponse acquireCustomer(@RequestBody AcquireCustomerRequest request) {
		return customerService.acquireCustomer(request);		
	}
	
	// DELETE http://localhost:7100/cm/api/v1/customers/jack@example.com
	@DeleteMapping("{email}")
	public CustomerResponse releaseCustomer(@PathVariable @Email String email) {
		return customerService.releaseCustomer(email);				
	}
}
