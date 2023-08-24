package com.customer.dto;

import java.sql.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.customer.constant.MessageConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * This is dto class which is used for validation and transfer the data in object.
 */
@Getter
@Setter
public class CustomerDto {

	private int id;

	@NotEmpty(message = MessageConstant.FIRST_NAME_REQUIRED)
	@Size(min = 3, max = 20, message = MessageConstant.FIRST_NAME_SIZE)
	private String firstName;

	@NotEmpty(message = MessageConstant.LAST_NAME_REQUIRED)
	@Size(min = 3, max = 20, message = MessageConstant.LAST_NAME_SIZE)
	private String lastName;

	@NotNull(message = MessageConstant.DOB_REQUIRED_MESSAGE) 
	private Date dateOfBirth;

	@Min(value = 1, message = MessageConstant.MIN_AGE_VALUE)
	@Max(value = 100, message = MessageConstant.MAX_AGE_VALUE)
	private int age;

	@NotNull(message = MessageConstant.GENDER_REQUIRED)
	private Byte gender;

	@NotEmpty(message = MessageConstant.EMAIL_REQUIRED)
	@Email(message = MessageConstant.EMAIL_INVALID)
	private String email;

	@NotEmpty(message = MessageConstant.MOBILE_REQUIRED)
	@Pattern(regexp = "\\d{10}", message = MessageConstant.MOBILE_INVALID)
	@Size(min = 10, max = 17, message = MessageConstant.MOBILE_SIZE)
	private String mobileNumber;

	@NotEmpty(message = MessageConstant.ADDRESS_REQUIRED)
	@Size(min = 10, message = MessageConstant.ADDRESS_SIZE)
	private String addressOne;

	@NotEmpty(message = MessageConstant.ADDRESS_REQUIRED)
	@Size(min = 10, message = MessageConstant.ADDRESS_SIZE)
	private String addressTwo;

}