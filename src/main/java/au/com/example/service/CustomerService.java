package au.com.example.service;

import au.com.example.entity.CustomerEntity;

public interface CustomerService {

	public CustomerEntity retrieve(Long id);
	
	public void save(CustomerEntity customer);
}
