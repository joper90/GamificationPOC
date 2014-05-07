package test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.testng.annotations.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestTests {

	
	private static String USER_NAME = "tibco-admin";
	private static String USER_GUID = "tibco-admin";
	
	@Test
	public static void TestGetPlayerData()
	{
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    String pDetails =  service.path("rest").path("userdetails")
	    			.queryParam("userName", USER_NAME)
	    			.queryParam("userGuid", USER_GUID)
	    			.accept(MediaType.APPLICATION_JSON)
	    			.get(String.class);
	    System.out.println(pDetails);
	}
	
	
	private static URI getBaseURI() {
		//http://localhost:8084/GamificationPOC/
		return UriBuilder.fromUri("http://localhost:8084/GamificationPOC").build();
	}
}
