package sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class AppTest extends JerseyTest {

	@Test
	public void testHello() throws Exception {
		String hello = target("app/hello").queryParam("name", "Ryu").request().get(String.class);
		assertThat(hello, is("Hello, Ryu!!"));
	}

	@Test
	public void testHelloPath() throws Exception {
		String hello = target("app/Ryu").request().get(String.class);
		assertThat(hello, is("Hello, Ryu!!"));
	}

	protected Application configure() {
		return new ResourceConfig(App.class);
	}
}
