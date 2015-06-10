package com.antonkharenko.booklib;

import com.antonkharenko.booklib.domain.User;
import com.codahale.metrics.health.HealthCheck;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.Path;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton Kharenko
 */
public class BooklibApplication extends Application<BooklibConfiguration> {

	private ApplicationContext context;

	public static void main(String[] args) throws Exception {
        new BooklibApplication().run(args);
    }

	public ApplicationContext getApplicationContext() {
		return context;
	}

    @Override
    public void initialize(Bootstrap<BooklibConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(BooklibConfiguration configuration, Environment environment) throws Exception {
        registerCORSFilter(environment);
		configureJsonSerialization(environment);
		registerSpringBeans(configuration, environment);
    }

	private void registerCORSFilter(Environment environment) {
		Map<String, String> filterInitParameters = new HashMap<>();
		filterInitParameters.put(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		filterInitParameters.put(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
		filterInitParameters.put(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		FilterRegistration.Dynamic CORSFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		CORSFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		CORSFilter.setInitParameters(filterInitParameters);
	}

	private void configureJsonSerialization(Environment environment) {
		environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	private void registerSpringBeans(BooklibConfiguration configuration, Environment environment) {
		context = initializeSpringContext(configuration);
		registerHealthChecks(environment);
		registerResources(environment);
		registerAuthenticator(environment);
	}

	private ApplicationContext initializeSpringContext(BooklibConfiguration configuration) throws BeansException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configuration.getSpringConfigLocation());
		context.registerShutdownHook();
		return context;
	}

	private void registerHealthChecks(Environment environment) {
		Map<String, HealthCheck> healthChecks = context.getBeansOfType(HealthCheck.class);
		for(Map.Entry<String,HealthCheck> entry : healthChecks.entrySet()) {
			environment.healthChecks().register(entry.getKey(), entry.getValue());
		}
	}

	private void registerResources(Environment environment) {
		Map<String, Object> resources = context.getBeansWithAnnotation(Path.class);
		for(Map.Entry<String,Object> entry : resources.entrySet()) {
			environment.jersey().register(entry.getValue());
		}
	}

	private void registerAuthenticator(Environment environment) {
		Authenticator<BasicCredentials, User> authenticator = context.getBean("authenticator", Authenticator.class);
		environment.jersey().register(AuthFactory.binder(
				new BasicAuthFactory<>(authenticator, "booklib", User.class)));
	}

}
