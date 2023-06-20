package com.nkxgen.spring.orm.filter;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

public class FilterInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws javax.servlet.ServletException {
		// Register the AuthenticationFilter with URL patterns
		FilterRegistration.Dynamic registration = servletContext.addFilter("authenticationFilter",
				new DelegatingFilterProxy("authenticationFilter"));
		registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/protected/*");
	}
}
