package sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("app")
public class App {

	@Path("hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(@QueryParam("name") String name) {
		return "Hello, " + name + "!!";
	}

	@Path("{name}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloPath(@PathParam("name") String name) {
		return "Hello, " + name + "!!";
	}
}
