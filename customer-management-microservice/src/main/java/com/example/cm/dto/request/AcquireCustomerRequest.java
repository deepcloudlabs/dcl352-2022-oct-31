package com.example.cm.dto.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.cm.domain.CustomerType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = "photo")
@EqualsAndHashCode(of = "email")
@NoArgsConstructor
public class AcquireCustomerRequest {
	@Email
	private String email;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Min(18)
	private int age;
	private String photo;
	private List<String> phones;
	@NotNull
	private CustomerType type;
}
