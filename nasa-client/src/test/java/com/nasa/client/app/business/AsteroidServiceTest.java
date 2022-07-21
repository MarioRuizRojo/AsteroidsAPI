package com.nasa.client.app.business;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nasa.client.app.Fixtures;
import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;
import com.nasa.client.app.models.dao.DayNeosDao;
import com.nasa.client.app.models.services.NasaService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
public class AsteroidServiceTest {	
	
	@MockBean
	private DayNeosDao dayNeosDao;
	
	@MockBean
	private NasaService nasaService;
	
	@InjectMocks
	private AsteroidService asteroidService;

	@BeforeAll
	public void setupAsteroidService(){
		when(dayNeosDao.findById(anyString()))
			.thenReturn( Mono.just( Fixtures.dayNeos1995_01_01Test() ) );
		when(nasaService.neoFeed(anyString(),anyString()))
			.thenReturn( Mono.just( Fixtures.neoFeed1995_01_02Test() ) );
	}
	
	@Test
	void tenClosestTest() {
		String start = "1995-01-01";
		String end = "1995-01-02";
		asteroidService.tenClosest(start, end)
		.subscribe((List<AsteroidClose> asteroids)->
			assertTrue(asteroids.size()==10)
				);
	}
	
	@Test
	void largestByYearTest() {
		String year = "1995";
		asteroidService.largestByYear(year)
		.subscribe((AsteroidLarge asteroid)->
			assertTrue(asteroid.equals(Fixtures.oneAsteroidForLargestByYearTest()))
				);
	}
	
}
