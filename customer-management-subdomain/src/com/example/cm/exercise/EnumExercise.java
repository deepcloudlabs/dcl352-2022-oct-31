package com.example.cm.exercise;

import com.example.cm.domain.CustomerType;
import com.example.cm.domain.Email;

public class EnumExercise {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var goldCustomer = CustomerType.valueOf("GOLD_CUSTOMER");
		System.out.println(goldCustomer);
		var jackEmail = Email.valueOf("jack@example.com");

	}

}
