package com.micro.gateway.config;

import com.micro.gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("auth", r -> r.path("/api/v1/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
					//.route("product", r -> r.path("/product/**").filters(f -> f.filter(filter)).uri("lb://product"))
				.route("product", r -> r.path("/api/v1/product/**").filters(f -> f.filter(filter)).uri("lb://product"))
				.route("category-service", r -> r.path("/api/v1/category/**").filters(f -> f.filter(filter)).uri("lb://category-service")).build();

	}

}
