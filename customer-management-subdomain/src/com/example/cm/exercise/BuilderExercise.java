package com.example.cm.exercise;

import com.example.cm.domain.Customer;

public class BuilderExercise {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var jack = new Customer.Builder()
				               .email("jack")
				               .birthYear(1967)
				               .fullName("jack", "shephard")
				               .build();

	}

}
