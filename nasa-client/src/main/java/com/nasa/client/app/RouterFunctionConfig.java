package com.nasa.client.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nasa.client.app.handler.AsteroidHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author Mario Ruiz Rojo
 * <br/>
 * REST service Routing Configuration
 * <br/>
 * All the REST service functions are explained in NasaHandler
 * Endpoints:
 * 	Ten Closest  					- GET  		/api/tenclosest
 * 	Largest by Year 				- GET  		/api/largestbyyear
 */
@Configuration
public class RouterFunctionConfig {	
	@Value("${config.urlTenClosest}")
	private String urlTenClosest;
	
	@Value("${config.urlLatestByYear}")
	private String urlLatestByYear;

	@Bean
	public RouterFunction<ServerResponse> routes(AsteroidHandler asteroidHandler){
		return route(GET(urlTenClosest),asteroidHandler::tenClosest)
				.andRoute(GET(urlLatestByYear), asteroidHandler::largestByYear);
	}
	
}
