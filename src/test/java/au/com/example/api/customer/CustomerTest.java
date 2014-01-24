package au.com.example.api.customer;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.http.HTTPContainerFactory;

public class CustomerTest extends JerseyTest {

	public static final String PACKAGE_NAME = "au.com.example.api.customer";

	public CustomerTest() {
		super(new WebAppDescriptor.Builder(PACKAGE_NAME).build());
	}

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new HTTPContainerFactory();
	}

	@Test
	public void testCustomerHelloResponse() {
		WebResource ws = resource().path("customer/hello");
		ClientResponse response = ws.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		
		assertEquals(200, response.getStatus());
	}
}
