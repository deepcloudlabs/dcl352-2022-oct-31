package com.example.cm.dto.response;

import java.util.List;

import com.example.cm.domain.CustomerType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@ToString(exclude = "photo")
public class CustomerResponse {
	private String email;
	private String firstName;
	private String lastName;
	private int age;
	private String photo;
	private List<String> phones;
	private CustomerType type;
}
