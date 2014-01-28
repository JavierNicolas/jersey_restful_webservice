package au.com.example.config;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import au.com.example.service.CustomerService;
import au.com.example.service.CustomerServiceImpl;

public class ApplicationBinder extends AbstractBinder {
	
    @Override
    protected void configure() {
        bind(CustomerServiceImpl.class).to(CustomerService.class).in(Singleton.class);
    }
}