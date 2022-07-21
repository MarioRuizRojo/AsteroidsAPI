package com.nasa.client.app.business;

import java.util.List;

import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;

import reactor.core.publisher.Mono;

public interface AsteroidService {
	public Mono<List<AsteroidClose>> tenClosest(String start, String end);
	public Mono<AsteroidLarge> largestByYear(String year);
}
