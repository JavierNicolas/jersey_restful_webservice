package au.com.example.api.customer;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class CustomerTest extends JerseyTest {
	
	@Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        
		return new ResourceConfig(CustomerResource.class); 
    }

	@Test
	public void testCustomerRetrieveResponse() {
		
		Response response = target().path("jrws").path("rest/customer/retrieve").queryParam("id", 2).request().get();
		
        assertEquals(200, response.getStatus());
	}
}
