package au.com.example.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import au.com.example.api.data.Customer;
import au.com.example.entity.CustomerEntity;
import au.com.example.service.CustomerService;

@Path(value = "customer")
public class CustomerResource {

	private final CustomerService customerService;

	@Inject
	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GET
	@Path(value = "retrieve/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id") Long id) {
		return entityToCustomer(customerService.retrieve(id));
	}
	
	@POST
	@Path(value = "save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveCustomer(Customer customer) {
		customerService.save(customerToEntity(customer));
		
		return Response.status(Status.OK).entity("customer has been successfully saved").type(MediaType.APPLICATION_JSON).build();
	}

	// =========== Helpers ================

	private Customer entityToCustomer(CustomerEntity entity) {
		Customer customer = new Customer();

		if (entity != null) {
			customer.setId(entity.getId());
			customer.setFirstName(entity.getFirstName());
			customer.setLastName(entity.getLastName());
		}
		
		return customer;
	}
	
	private CustomerEntity customerToEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();

		if (customer != null) {
			entity.setId(customer.getId());
			entity.setFirstName(customer.getFirstName());
			entity.setLastName(customer.getLastName());
		}
		
		return entity;
	}

}
