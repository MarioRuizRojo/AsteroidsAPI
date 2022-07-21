package com.nasa.client.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nasa.client.app.business.AsteroidService;
import com.nasa.client.app.handler.AsteroidHandler;
import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;

import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK)//simulated server
class SpringBootWebfluxApiRestApplicationTests {

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
	
	@MockBean
	private AsteroidService asteroidService;
	
	@InjectMocks
	private AsteroidHandler asteroidHandler;	
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(
				GET(urlTenClosest), asteroidHandler::tenClosest)
			.andRoute(GET(urlLatestByYear), asteroidHandler::largestByYear);
		client = WebTestClient.bindToRouterFunction(routerFunction).build();
	}
	
	@BeforeAll
	public void setupAsteroidService(){
		when(asteroidService.tenClosest(anyString(),anyString()))
				.thenReturn( Mono.just( Fixtures.generate10ClosestListTest() ) );
		when(asteroidService.largestByYear(anyString()))
		.thenReturn( Mono.just( Fixtures.oneAsteroidForLargestByYearTest() ) );
	}

	@Test
	void tenClosestTest() {
		String start = "1995-01-01";
		String end = "1997-02-22";
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
			AsteroidClose responsedAsteroid = asClos.get(0);
			List<AsteroidClose> expectedList = Fixtures.generate10ClosestListTest();
			AsteroidClose expectedAsteroid =  expectedList.get(expectedList.indexOf(responsedAsteroid));
			String resName = responsedAsteroid.getName();
			String expName = expectedAsteroid.getName();
			assertTrue(expName.equals(resName));
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
			AsteroidLarge expectedAsteroid =  Fixtures.oneAsteroidForLargestByYearTest();
			String resName = responsedAsteroid.getName();
			String expName = expectedAsteroid.getName();
			assertTrue(expName.equals(resName));
		});
	}
}
