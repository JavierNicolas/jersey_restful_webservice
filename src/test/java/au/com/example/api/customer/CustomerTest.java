package au.com.example.api.customer;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
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

import au.com.example.service.customer.CustomerService;
import au.com.example.service.customer.data.CustomerEntity;

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
	 * Invoke the retrieve customer endpoint and check the http response is 200.
	 */
	@Test
	public void testCustomerRetrieveResponse() {
		
		long id = 2;
		
		Mockito.when(serviceMock.getCustomer(Mockito.anyLong())).thenReturn(getMockCustomer(id));

		Response response = target("customer/retrieve").queryParam("id", id).request().get();

		assertEquals(200, response.getStatus());
	}
	
	// ======= Mocking ==========
	
	/**
	 * Mock object that will be returned.
	 * 
	 * @param id the id of the customer
	 * 
	 * @return the customer object
	 */
	private CustomerEntity getMockCustomer(Long id) {
		return new CustomerEntity(id, "Test", "Customer");
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
			bindFactory(this).to(CustomerService.class);
		}

		public CustomerService provide() {
			return serviceMock;
		}

		public void dispose(CustomerService service) {
			serviceMock = null;
		}
	}
}
