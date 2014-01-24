package au.com.example.api.customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "customer")
public class CustomerResource {
	
	@GET
	@Path(value = "hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Sir";
	}
}
