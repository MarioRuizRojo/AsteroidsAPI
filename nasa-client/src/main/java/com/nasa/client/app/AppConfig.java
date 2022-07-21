package com.nasa.client.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 
 * @author Mario Ruiz Rojo
 * 
 * Main Spring App configuration to setup the REST api nasa client connection
 *
 */
@Component
public class AppConfig {
	
	//Asteroids - NeoWs
	@Value("${api.nasa.url}")
	private String urlAPInasa;
	
	@Bean
	public WebClient registerWebClient() {
		return WebClient.create(urlAPInasa);
	}
}
