package com.nasa.client.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {
	
	@Value("${config.urlTenClosest}")
	private String urlTenClosest;
	
	@Value("${config.urlLatestByYear}")
	private String urlLatestByYear;
	
	@Value("${config.paramStartDate}")
	private String paramStartDate;
	
	@Value("${config.paramEndDate}")
	private String paramEndDate;
	
	@Value("${config.paramYear}")
	private String paramYear;
	
	@Autowired
	private WebTestClient client;
	
	
	@Test
	void tenClosestTest() {
		String start = "1995-01-01";
		String end = "1995-01-08";
		client.get()
		.uri(uriBuilder -> uriBuilder
				.path(urlTenClosest)
			    .queryParam(paramStartDate, start)
			    .queryParam(paramEndDate, end)
			    .build())
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(AsteroidClose.class)
		.consumeWith(response -> {
			List<AsteroidClose> asClos = response.getResponseBody();
			assertTrue(asClos.size()>0);
			assertTrue(asClos.size()==10);
		});
	}
	
	@Test
	void largestByYearTest() {
		String year = "1995";
		client.get()
		.uri(uriBuilder -> uriBuilder
				.path(urlLatestByYear)
			    .queryParam(paramYear, year)
			    .build())
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(AsteroidLarge.class)
		.consumeWith(response -> {
			AsteroidLarge responsedAsteroid = response.getResponseBody();
			assertTrue(responsedAsteroid!=null);
		});
	}
}
