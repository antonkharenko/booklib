package com.antonkharenko.booklib.integration;

import com.antonkharenko.booklib.BooklibApplication;
import com.antonkharenko.booklib.BooklibConfiguration;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.api.UserResponse;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.MongodProcess;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Anton Kharenko
 */
public abstract class AbstractServiceIT {

	private final static String TARGET_URL = "http://localhost:8080";
	private final static String EMPTY_API_KEY = "empty";
	private final static String DB_NAME = "booklib";

	@ClassRule
	public static final DropwizardAppRule<BooklibConfiguration> RULE =
			new DropwizardAppRule<BooklibConfiguration>(BooklibApplication.class, "booklib-integration-test.yml");

	private static MongodProcess mongoProcess;
	private static Mongo mongo;
	private static Client client;

	@BeforeClass
	public static void init(){
		BooklibApplication application = RULE.getApplication();
		ApplicationContext applicationContext = application.getApplicationContext();
		mongoProcess = (MongodProcess) applicationContext.getBean("mongoProcess");
		mongo = (Mongo) applicationContext.getBean("mongo");

		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
		client = ClientBuilder.newClient(clientConfig);
		client.register(feature);
	}

	@AfterClass
	public static void destroy() {
		mongo.close();
		mongoProcess.stop();
		client.close();
	}

	protected static void dropDatabase() {
		mongo.dropDatabase(DB_NAME);
	}

	protected static <Req, Resp> Resp request(String path, String method, Req request, Class<Resp> response) {
		return request(path, method, request, response, EMPTY_API_KEY);
	}

	protected static <Req, Resp> Resp request(String path, String method, Req request, Class<Resp> response, String apiKey) {
		return request(path, method, Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), response, apiKey);
	}

	protected static <Req, Resp> Resp request(String path, String method, Entity<Req> requestEntity, Class<Resp> response) {
		return request(path, method, requestEntity, response, EMPTY_API_KEY);
	}

	protected static <Req, Resp> Resp request(String path, String method, Entity<Req> requestEntity, Class<Resp> response, String apiKey) {
		return request(path, method, requestEntity, response, apiKey, new String[0]);
	}

	protected static <Req, Resp> Resp request(
			String path, String method, Entity<Req> requestEntity, Class<Resp> response, String apiKey, String... queryParams) {
		return webTarget(path, queryParams).request()
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, apiKey)
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, "")
				.method(
						method,
						requestEntity,
						response
				);
	}

	protected static <Req, Resp> Resp request(
			String path, String method, Entity<Req> requestEntity, GenericType<Resp> response, String apiKey, String... queryParams
	) {
		return webTarget(path, queryParams).request()
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, apiKey)
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, "")
				.method(
						method,
						requestEntity,
						response
				);
	}

	private static WebTarget webTarget(String path, String... queryParams) {
		WebTarget webTarget = client.target(TARGET_URL).path(path);
		for (int i = 0; i < queryParams.length; i+=2) {
			webTarget = webTarget.queryParam(queryParams[i], queryParams[i + 1]);
		}
		return webTarget;
	}

	protected static UserResponse registerUser() {
		final SignUpRequest signUpRequest = createSignUpRequest();
		final UserResponse userResponse = request("/api/account/signup", "POST", signUpRequest, UserResponse.class);
		assertNotNull(userResponse);
		assertNotNull(userResponse.getApiKey());
		assertEquals(signUpRequest.getUsername(), userResponse.getUsername());
		return userResponse;
	}

	protected static SignUpRequest createSignUpRequest() {
		return SignUpRequest.newBuilder()
				.username("johndoe")
				.password("qwerty")
				.email("johndoe@example.com")
				.firstName("John")
				.lastName("Doe")
				.build();
	}

}
