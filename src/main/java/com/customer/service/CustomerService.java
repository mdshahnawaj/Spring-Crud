package com.customer.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.customer.dto.CustomerDto;
import com.customer.model.CustomerEntity;

/*
 * This interface is used to perform customer related crud operation
 */
public interface CustomerService {

	public ResponseEntity<Object> saveDetails(CustomerDto customerDto);

	public List<CustomerEntity> getAllCustomer();

	public CustomerEntity getCustomerById(int id);

	public void deleteById(int id);

	public String deleteAllCustomer();

}