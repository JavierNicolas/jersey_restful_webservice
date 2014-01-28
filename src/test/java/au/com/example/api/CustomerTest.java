package au.com.example.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.inject.Singleton;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import au.com.example.api.data.Customer;
import au.com.example.entity.CustomerEntity;
import au.com.example.service.CustomerService;

public class CustomerTest extends JerseyTest {

	@Mock
	private CustomerService serviceMock;

	/**
	 * This is executed only once, not before each test.
	 * 
	 * This will enable Mockito Annotations to be used.
	 * This will enable log traffic and message dumping.
	 * This will register the Injectable Provider to the ResourceConfiguration which will
	 * allow for the mock objects and jersey test to be linked.
	 */
	@Override
	protected Application configure() {
		MockitoAnnotations.initMocks(this);
		
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		ResourceConfig config = new ResourceConfig(CustomerResource.class);
		config.register(new InjectableProvider());

		return config;
	}

	/**
	 * Invoke the retrieve customer and check the http response is 200.
	 */
	@Test
	public void testCustomerRetrieveResponse() {
		
		long id = 2;
		
		when(serviceMock.retrieve(Mockito.anyLong())).thenReturn(getMockCustomerEntity(id));

		Response response = target("customer/retrieve/" + id).request().get();

		Customer customer = response.readEntity(Customer.class);
		
		assertEquals(200, response.getStatus());
		assertEquals("2", customer.getId().toString());
		assertEquals("Test", customer.getFirstName());
		assertEquals("Customer", customer.getLastName());
	}
	
	/**
	 * Invoke the save customer and check the http response is 200.
	 */
	@Test
	public void testCustomerSaveResponse() {
		
	    Entity<Customer> customerEntity = Entity.entity(getMockCustomer(), MediaType.APPLICATION_JSON_TYPE);
	    
		doNothing().when(serviceMock).save(Mockito.any(CustomerEntity.class));

		Response response = target("customer/save").request().post(customerEntity);

		assertEquals(200, response.getStatus());
		assertEquals("customer has been successfully saved", response.readEntity(String.class));
	}

	// ======= Mocking ==========
	
	/**
	 * Mock object that will be returned.
	 * 
	 * @param id the id of the customer
	 * 
	 * @return the customer entity object
	 */
	private CustomerEntity getMockCustomerEntity(Long id) {
		return new CustomerEntity(id, "Test", "Customer");
	}
	
	/**
	 * Mock object that will be returned
	 * 
	 * @return the customer object
	 */
	private Customer getMockCustomer() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("Robert");
		customer.setLastName("Leggett");
		
		return customer;
	}

	/**
	 * Create an Injectable Provider that with bind this factory to the customer service.
	 * When the provide is invoked a mock service object will be returned.
	 * When dispose is invoked the mock service object will be assigned null.
	 * 
	 * @author Robert Leggett
	 *
	 */
	class InjectableProvider extends AbstractBinder implements Factory<CustomerService> {
		
		@Override
		protected void configure() {
			bindFactory(this).to(CustomerService.class).in(Singleton.class);
		}

		public CustomerService provide() {
			return serviceMock;
		}

		public void dispose(CustomerService service) {
			serviceMock = null;
		}
	}
}
