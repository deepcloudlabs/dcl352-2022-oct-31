package com.example.cm.document;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.cm.domain.CustomerType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customers")
@Data
@NoArgsConstructor
public class CustomerDocument {
	@Id
	@Email
	private String email;
	@Field(name="fn")
	@Indexed
	@NotBlank
	private String firstName;
	@Field(name="ln")
	@Indexed
	@NotBlank
	private String lastName;
	@Min(18)
	private int age;	
	private String photo;
	private List<String> phones;
	@NotNull
	private CustomerType type;
}
