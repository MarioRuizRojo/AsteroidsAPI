package com.nasa.client.app.business;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasa.client.app.models.Asteroid;
import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;
import com.nasa.client.app.models.dao.DayNeosDao;
import com.nasa.client.app.models.services.NasaService;
import com.nasa.client.app.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AsteroidServiceImpl implements AsteroidService{

	@Autowired
	private NasaService nasaService;
	
	@Autowired
	private DayNeosDao dayNeosDao;
	
	/**
	 * It returns a list of asteroids compared by diameter of that year
	 * @param year in yyyy-MM-dd string format
	 * @return asteroids of that year
	 */
	private Mono<List<Asteroid>> getAsteroidsByYear(String year){
		return Utils.allDaysOfYear(year)
			.delayElements(Duration.ofMillis(50L))
			.flatMap((String day)->getDayAsteroids(day, AsteroidLarge.class))
			.flatMap( (List<Asteroid> asteroids) -> Flux.fromIterable(asteroids))
			.collectList();
	}
	
	/**
	 * It returns a list of asteroids compared by close distance to earth in that period
	 * @param start the start date of the date's range
	 * @param end the end date of the date's range
	 * @return  asteroids of that period
	 */
	private Mono<List<Asteroid>> getAsteroidsInDateRange(String start, String end) {
		return Utils.allDaysOfDateRange(start,end)
				.delayElements(Duration.ofMillis(50L))
				.flatMap(day->getDayAsteroids(day, AsteroidClose.class))
				.flatMap( (List<Asteroid> asteroids) -> Flux.fromIterable(asteroids))
				.collectList();
	}

	/**
	 * It returns a list of asteroids that passed close to earth that day
	 * It checks if that neo data is in mongo DB 
	 * if it exists in DB then 
	 * 	it return it from DB 
	 * else 
	 * 	it triggers one call to nasa's api
	 * @param day in string format yyyy-MM-dd
	 * @param AsteroidType what kind of comparison between asteroids will be perform later on
	 * @return asteroids of that day
	 */
	private Mono<List<Asteroid>> getDayAsteroids(String day, Class<?> AsteroidType) {		
		return dayNeosDao.existsById(day)
				.flatMap( (Boolean exists) -> {
					if(exists){//get asteroids from DB
						return dayNeosDao.findById(day)
								.flatMap(dayNeos -> ModelConverter.dayNeosToAsteroids(dayNeos,AsteroidType));
					}
					else {//get asteroids from NASA API
						return nasaService.neoFeed(day,day)
								.flatMap(neofr->ModelConverter.neoResultToAsteroids(neofr, AsteroidType))
								.map((List<Asteroid> asteroids)->{
									ModelConverter.asteroidsToDayNeos(asteroids,day)
									.flatMap(dayNeos->dayNeosDao.save(dayNeos))
									.subscribe();//TODO: if not saved some log
									return asteroids;
								});
					}
				});						
	}

	//------------------------------------------------------------
	//----------------------------PUBLIC--------------------------
	//------------------------------------------------------------
	/**
	 * It returns a list of 10 closets to earth asteroids in that period
	 * @param start the start date of the date's range
	 * @param end the end date of the date's range
	 */
	@Override
	public Mono<List<AsteroidClose>> tenClosest(String start, String end) {
		return getAsteroidsInDateRange(start,end)
		.map(asteroidList->{
			@SuppressWarnings("unchecked")
			List<AsteroidClose> asteroidsClose = (List<AsteroidClose>)(List<?>) asteroidList;
			Collections.sort(asteroidsClose);
			return asteroidsClose.subList(0, 10);
		});
	}	

	/**
	 * It returns all details of the largest asteroid of that year
	 * @param year in string format yyyy
	 */
	@Override
	public Mono<AsteroidLarge> largestByYear(String year) {
		return getAsteroidsByYear(year)
		.map(asteroidList->{
			@SuppressWarnings("unchecked")
			List<AsteroidLarge> asteroidsLarge = (List<AsteroidLarge>)(List<?>) asteroidList;
			Collections.sort(asteroidsLarge);
			return asteroidsLarge.get(0);
		});
	}

}
