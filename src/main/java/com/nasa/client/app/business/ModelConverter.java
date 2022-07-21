package com.nasa.client.app.business;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nasa.client.app.models.Asteroid;
import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;
import com.nasa.client.app.models.documents.DayNeos;
import com.nasa.client.app.models.json.NEO;
import com.nasa.client.app.models.json.NeoFeedResult;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ModelConverter {
	
	private static Asteroid neoToAsteroid(NEO neo, Class<?> AsteroidType) {
		Asteroid as = null;//mandatory polymorphism
		if(AsteroidType.getName().equals(AsteroidClose.class.getName())) {
			as = new AsteroidClose(neo);
		}
		else {
			as = new AsteroidLarge(neo);
		}
		return as;
	}

	/**
	 * It converts the Near Earth Object Feed API result into a list of Asteroid
	 * @param neofr
	 * @param AsteroidType
	 * @return
	 */
	public static Mono<List<Asteroid>> neoResultToAsteroids(NeoFeedResult neofr, Class<?> AsteroidType){		
		return Flux.fromIterable(neofr.getNear_earth_objects().entrySet())
		.map( (Map.Entry<String,List<NEO>> yearEntry) -> yearEntry.getValue() )
		.flatMap( (List<NEO> neos) -> Flux.fromIterable(neos) )
		.map(neo->{
			return neoToAsteroid(neo,AsteroidType);
		})
		.collectList();		
	}

	public static Mono<List<Asteroid>> dayNeosToAsteroids(DayNeos dayNeos, Class<?> AsteroidType) {
		return Mono.just(dayNeos)
				.map(dayNeos1->{
					Type listOfClos = new TypeToken<ArrayList<AsteroidClose>>() {}.getType();
					Type listOfLar = new TypeToken<ArrayList<AsteroidLarge>>() {}.getType();
					Gson gson = new Gson();
					List<Asteroid> asteroids = null;//mandatory polymorphism
					if(AsteroidType.getName().equals(AsteroidClose.class.getName())) {
						asteroids = gson.fromJson(dayNeos1.getJson(), listOfClos);
					}
					else {
						asteroids = gson.fromJson(dayNeos1.getJson(), listOfLar);
					}		
					return asteroids;
				});		
	}

	public static Mono<DayNeos> asteroidsToDayNeos(List<Asteroid> asteroids, String day) {
		return Mono.just(asteroids)
		.map(asteroidsL->{
			Gson gson = new Gson();
			String json = gson.toJson(asteroidsL);
			return new DayNeos(day,json);
		});		
	}
}
