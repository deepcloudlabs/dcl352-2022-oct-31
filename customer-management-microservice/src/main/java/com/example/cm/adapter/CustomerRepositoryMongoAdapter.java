package com.example.cm.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.cm.document.CustomerDocument;
import com.example.cm.domain.Customer;
import com.example.cm.domain.Email;
import com.example.cm.repository.CustomerDocumentRepository;
import com.example.cm.repository.CustomerRepository;

@Service
public class CustomerRepositoryMongoAdapter implements CustomerRepository {
	private final CustomerDocumentRepository customerDocumentRepository;
	private final ModelMapper modelMapper;

	public CustomerRepositoryMongoAdapter(CustomerDocumentRepository customerDocumentRepository,
			ModelMapper modelMapper) {
		this.customerDocumentRepository = customerDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean exists(Email email) {
		return customerDocumentRepository.existsById(email.getValue());
	}

	@Override
	public Customer create(Customer customer) {
		var document = modelMapper.map(customer, CustomerDocument.class);
		var persistedDocument = customerDocumentRepository.save(document);
		return modelMapper.map(persistedDocument, Customer.class);
	}

	@Override
	public Optional<Customer> findCustomerByEmail(Email email) {
		return customerDocumentRepository.findById(email.getValue())
				.map(document -> modelMapper.map(document, Customer.class));
	}

	@Override
	public void removeCustomer(Customer customer) {
		customerDocumentRepository.deleteById(customer.getEmail().getValue());
	}

}
