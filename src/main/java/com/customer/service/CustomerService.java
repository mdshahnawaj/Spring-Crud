package com.customer.service;

import java.util.List;
import com.customer.dto.CustomerDto;
import com.customer.model.CustomerEntity;

/*
 * This interface is used to perform customer related crud operation
 */
public interface CustomerService {

	public CustomerDto saveDetails(CustomerDto customerDto);

	public List<CustomerEntity> getAllCustomer();

	public CustomerEntity getCustomerById(int id);

	public void deleteById(int id);

	public String deleteAllCustomer();

}