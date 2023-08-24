package com.customer.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.constant.MessageConstant;
import com.customer.dto.CustomerDto;
import com.customer.exception.ResourceNotFoundException;
import com.customer.model.CustomerEntity;
import com.customer.service.CustomerService;

/**
 * This is controller class is used for perform crud rest api operation
 * @author MdShahnawaj
 */
@RequestMapping("/customer")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * This end-point used to save customer details and check email and mobile validation
	 * @param customerDto
	 * @return response
	 */
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody CustomerDto customerDto) {
		ResponseEntity<Object> response = customerService.saveDetails(customerDto);
		return response;
	}

	/**
	 * This end-point used to get all customer details
	 * @return customer
	 */
	@GetMapping
	public ResponseEntity<Object> read() {
		List<CustomerEntity> customer = customerService.getAllCustomer();

		if (customer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageConstant.RECORD_NOT_FOUND_MESSAGE);
		} else {
			return ResponseEntity.ok(customer);
		}
	}

	/**
	 * This end-point used to get customer details by id
	 * @return customerDetails
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable int id) {
		try {
			CustomerEntity customerDetails = customerService.getCustomerById(id);
			return ResponseEntity.ok(customerDetails);
		} catch (ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	/**
	 * This end-point used to update customer details.
	 * @param customerDto
	 * @return response
	 */
	@PutMapping
	public ResponseEntity<Object> update(@Valid @RequestBody CustomerDto customerDto) {
		ResponseEntity<Object> response = customerService.saveDetails(customerDto);
		return response;
	}

	/**
	 * This end-point used to delete customer details by id
	 * @param id
	 * @return response message
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		try {
			customerService.deleteById(id);
			return ResponseEntity.ok("Customer with ID " + id + " has been deleted");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	/**
	 * This end-point used to delete all customer details from database
	 * @return response message
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteAllCustomers() {
		try {
			String responseMessage = customerService.deleteAllCustomer();
			return ResponseEntity.ok(responseMessage);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}