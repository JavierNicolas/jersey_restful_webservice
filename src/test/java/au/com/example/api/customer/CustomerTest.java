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

import au.com.example.service.customer.CustomerService;
import au.com.example.service.customer.data.CustomerEntity;

public class CustomerTest extends JerseyTest {

	@Mock
	private CustomerService serviceMock;

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		ResourceConfig config = new ResourceConfig(CustomerResource.class);
		config.register(new InjectableProvider());

		return config;
	}

	@Test
	public void testCustomerRetrieveResponse() {
		
		long id = 2;
		
		Mockito.when(serviceMock.getCustomer(Mockito.anyLong())).thenReturn(getMockCustomer(id));

		Response response = target("customer/retrieve").queryParam("id", id).request().get();

		assertEquals(200, response.getStatus());
	}
	
	// ======= Mocking ==========
	
	private CustomerEntity getMockCustomer(Long id) {
		return new CustomerEntity(id, "Test", "Customer");
	}

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
