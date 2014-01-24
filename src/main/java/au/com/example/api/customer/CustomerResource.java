package au.com.example.api.customer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import au.com.example.api.customer.data.Customer;
import au.com.example.service.customer.CustomerService;
import au.com.example.service.customer.data.CustomerEntity;

@Path(value = "customer")
public class CustomerResource {
	
	private final CustomerService customerService;
	
	@Inject
	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GET
	@Path(value = "retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@QueryParam("id") Long id) {
		return entityToCustomer(customerService.getCustomer(id));
	}
	
	// =========== Helpers ================
	
	private Customer entityToCustomer(CustomerEntity entity) {
		Customer customer = new Customer();
		customer.setId(entity.getId());
		customer.setFirstName(entity.getFirstName());
		customer.setLastName(entity.getLastName());
		
		return customer;
	}
}
