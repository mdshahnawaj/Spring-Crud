package com.customer.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This is entity class used for create table and also declared fields and getter or setter.
 */
@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name", length = 20, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 20, nullable = false)
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "gender", length = 10, nullable = false)
	private byte gender;

	@Column(name = "email", length = 70, unique = true, nullable = false)
	private String email;

	@Column(name = "mobile_number", length = 17, unique = true, nullable = false)
	private String mobileNumber;

	@Column(name = "address_one", length = 255, nullable = false)
	private String addressOne;

	@Column(name = "address_two", length = 255, nullable = false)
	private String addressTwo;

}