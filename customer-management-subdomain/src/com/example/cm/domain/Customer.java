package com.example.cm.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Bounded-Context -> Design
// Sub-domain (discover) -> Bounded-Context (design)
// Ubiquitous Language: Customer, Email, FullName, Photo, ... 
//                      Business Methods
// Entity Class (DDD)
// 1. Identity
// 2. Mutable
@DomainEntity(identity = "email", aggregate = true)
public class Customer {
	private Email email;
	private FullName fullName;
	private BirthYear birthYear;
	private Photo photo;
	private List<Phone> phones = new ArrayList<>();
	private CustomerType customerType;

	// constructor
	public Customer(Email email, FullName fullName, BirthYear birthYear, List<Phone> phones,
			CustomerType customerType) {
		this.email = email;
		this.fullName = fullName;
		this.birthYear = birthYear;
		this.phones = phones;
		this.customerType = customerType;
	}

	public Customer(Email email, FullName fullName, BirthYear birthYear, Photo photo, List<Phone> phones,
			CustomerType customerType) {
		this.email = email;
		this.fullName = fullName;
		this.birthYear = birthYear;
		this.photo = photo;
		this.phones = phones;
		this.customerType = customerType;
	}

	public Customer(Builder builder) {
		this.email = builder.email;
		this.fullName = builder.fullName;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
		this.phones = builder.phones;
		this.customerType = builder.customerType;
	}

	public void upgradeCustomerType() {
		this.customerType = this.customerType.upgrade();
	}

	public Email getEmail() {
		return email;
	}

	public FullName getFullName() {
		return fullName;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public List<Phone> getPhones() {
		return List.copyOf(phones);
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void migrateEmail(Email newEmail) {
		// TODO
	}

	public boolean addNewPhone(Phone newPhone) {
		if (this.phones.stream().noneMatch(p -> p.value().equals(newPhone.value()))) {
			this.phones.add(newPhone);
			return true;
		}
		return false;
	}

	public static class Builder {
		private Email email;
		private FullName fullName;
		private BirthYear birthYear;
		private Photo photo;
		private List<Phone> phones = new ArrayList<>();
		private CustomerType customerType;

		public Builder email(String value) {
			this.email = Email.valueOf(value);
			return this;
		}

		public Builder fullName(String firstname, String lastName) {
			this.fullName = FullName.of(firstname, lastName);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.valuesOf(values);
			return this;
		}

		public Builder photo(String base64Value) {
			this.photo = Photo.valuesOf(base64Value);
			return this;
		}

		public Builder phones(List<String> phones) {
			this.phones = phones.stream().map(Phone::new).toList();
			return this;
		}

		public Builder phones(String... phones) {
			this.phones = Arrays.stream(phones).map(Phone::new).toList();
			return this;
		}

		public Builder customerType(String value) {
			this.customerType = CustomerType.valueOf(value);
			return this;
		}

		public Builder customerType(CustomerType customerType) {
			this.customerType = customerType;
			return this;
		}

		public Customer build() {
			// Business Rule
			// Validation
			// Invariants
			// Constraint
			// Policy
			return new Customer(this);
		}
	}
}
