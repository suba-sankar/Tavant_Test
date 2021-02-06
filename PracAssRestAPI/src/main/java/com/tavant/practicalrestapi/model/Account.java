package com.tavant.practicalrestapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account  {
	
	@Id
	private Integer accountNumber;
	
	
	@NotBlank(message = "firstName should not be blank")
	private String firstName;
	
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "lastName should not be blank")
	private String lastName;
	
	@NotBlank(message = "address should not null")
	//@Transient
	private String address;
	
	@NotBlank(message = "email should not blank")
	@Email(message = "email should be valid")
	private String email;

	
	
	
	
	
	
}