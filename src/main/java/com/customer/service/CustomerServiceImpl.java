package com.customer.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.customer.constant.MessageConstant;
import com.customer.dto.CustomerDto;
import com.customer.exception.ResourceNotFoundException;
import com.customer.model.CustomerEntity;
import com.customer.repository.CustomerRepository;

/**
 * This class is used to implement method
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * This method is used to save customer details in database.
	 * @param customerDto
	 * @return response message
	 */

	@Override
	public ResponseEntity<Object> saveDetails(CustomerDto customerDto) {
		int id = customerDto.getId();
		String email = customerDto.getEmail();
		String mobileNumber = customerDto.getMobileNumber();

		boolean existsEmail = customerRepository.existsByEmailAndIdNot(email, id);
		boolean existsMobileNumber = customerRepository.existsByMobileNumberAndIdNot(mobileNumber, id);
		if (existsEmail) {
			return ResponseEntity.status(HttpStatus.OK).body(MessageConstant.EMAIL_ALREADY_EXISTS);
		} else if (existsMobileNumber) {
			return ResponseEntity.status(HttpStatus.OK).body(MessageConstant.MOBILE_NUMBER_ALREADY_EXISTS);
		}
		CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
		customerRepository.save(customerEntity);

		if (id != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(MessageConstant.UPDATE_SUCCESS_MESSAGE);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstant.SAVE_SUCCESS_MESSAGE);
		}
	}

	/**
	 * This method is used to get all customer details from database.
	 * @return customerDetails
	 */
	@Override
	public List<CustomerEntity> getAllCustomer() {
		List<CustomerEntity> customer = customerRepository.findAll();
		return customer;
	}

	/**
	 * This method is used to get customer details by id from database.
	 * @param id
	 * @return customerDetails
	 */
	@Override
	public CustomerEntity getCustomerById(int id) {
		CustomerEntity customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageConstant.ID_NOT_FOUND));
		return customer;
	}

	/**
	 * This method is used to delete customer details by id from database.
	 * @param id
	 * @throw exception
	 */
	@Override
	public void deleteById(int id) {
		if (!customerRepository.existsById(id)) {
			throw new ResourceNotFoundException(MessageConstant.ID_NOT_FOUND);
		}
		customerRepository.deleteById(id);
	}

	/**
	 * This method is used to delete all customer details from database.
	 * @throw exception
	 * @return message
	 */
	@Override
	public String deleteAllCustomer() {
		long deletedCount = customerRepository.count();

		if (deletedCount > 0) {
			customerRepository.deleteAll();
			return MessageConstant.DELETE_ALL_CUSTOMER;
		} else {
			throw new RuntimeException(MessageConstant.ID_NOT_FOUND);
		}
	}

}