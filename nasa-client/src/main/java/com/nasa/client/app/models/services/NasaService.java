package com.nasa.client.app.models.services;

import com.nasa.client.app.models.json.NeoFeedResult;

import reactor.core.publisher.Mono;

/**
 * 
 * @author Mario Ruiz Rojo
 * Bussines layer interface for managing REST api nasa
 */
public interface NasaService {
	public Mono<NeoFeedResult> neoFeed(String start, String end);
}