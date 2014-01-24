package au.com.example.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import au.com.example.service.customer.CustomerService;
import au.com.example.service.customer.CustomerServiceImpl;

public class ApplicationBinder extends AbstractBinder {
	
    @Override
    protected void configure() {
    	System.out.println("Binding Objects");
    	
        bind(CustomerServiceImpl.class).to(CustomerService.class);
        
        System.out.println("Binding Completed");
    }
}