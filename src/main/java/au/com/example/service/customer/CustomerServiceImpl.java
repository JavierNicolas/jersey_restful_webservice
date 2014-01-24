package au.com.example.service.customer;

import au.com.example.service.customer.data.CustomerEntity;

public class CustomerServiceImpl implements CustomerService {

	public CustomerEntity getCustomer(Long id) {
		// TODO implement a DAO
		
		return new CustomerEntity(id, "Robert", "Leggett");
	}
}
