package com.customer.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.constant.MessageConstant;
import com.customer.dto.CustomerDto;
import com.customer.exception.EmailOrMobileAlreadyExists;
import com.customer.exception.ResourceNotFoundException;
import com.customer.model.CustomerEntity;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;

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
	 * This method is used to save customer details in database
	 * @param customerDto
	 * @return response
	 */
	@Override
	public CustomerDto saveDetails(CustomerDto customerDto) {
		int emailId = customerDto.getId();
		int mobileId = customerDto.getId();

		boolean existsEmailOrMobile = customerRepository.existsByEmailAndIdNotOrMobileNumberAndIdNot(customerDto.getEmail(), emailId, customerDto.getMobileNumber(), mobileId);
		if (existsEmailOrMobile) {
			throw new EmailOrMobileAlreadyExists(MessageConstant.EMAIL_MOBILE_EXISTS_MESSAGE);
		}
		CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
		customerRepository.save(customerEntity);
		return modelMapper.map(customerEntity, CustomerDto.class);
	}

	/**
	 * This method is used to get all customer details from database
	 * @return customerDetails
	 */
	@Override
	public List<CustomerEntity> getAllCustomer() {
		return customerRepository.findAll();
	}

	/**
	 * This method is used to get customer details by id from database
	 * @param id
	 * @throw exception
	 * @return customerDetails
	 */
	@Override
	public CustomerEntity getCustomerById(int id) {
		return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageConstant.ID_NOT_FOUND));
	}

	/**
	 * This method is used to delete customer details by id from database
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
	 * This method is used to delete all customer details from database
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
			throw new RuntimeException(MessageConstant.RECORD_NOT_FOUND);
		}
	}

}