package com.nasa.client.app.handler;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nasa.client.app.business.AsteroidService;
import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;

import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.*;

import java.util.List;
import java.util.Optional;

import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;

/**
 * 
 * @author Mario Ruiz Rojo
 * <br/>
 * Request hanlder for every REST endpoint
 * <br/>
 * All functions to manage Products and view their price converted to dollars
 */
@Component
public class AsteroidHandler {
	
	@Autowired
	private AsteroidService asteroidService;
			
	@Value("${api.nasa.url}")
	private String urlClient;
	
	@Value("${config.paramStartDate}")
	private String paramStartDate;
	
	@Value("${config.paramEndDate}")
	private String paramEndDate;
	
	@Value("${config.paramYear}")
	private String paramYear;
	
	/**
	 * It catches all errors of the current stream and creates a REST http response with bad request code
	 * @param throwa is the exception of the stream with the error message
	 * @return server response with bad request code and error message
	 */
	private Mono<ServerResponse> responseErrors(Throwable throwa){
		return Mono.just(throwa).cast(WebClientResponseException.class)
				.flatMap(error -> {
					switch(error.getStatusCode()) {
					case BAD_REQUEST:
						return ServerResponse.badRequest()
								.contentType(MediaType.APPLICATION_JSON)
								.body(fromValue(error.getResponseBodyAsString()));
					case NOT_FOUND:
						return ServerResponse.notFound().build();
					default:
						return Mono.error(error);
					}						
				});
	}
	
	/**
	 * It returns the list of asteroids as REST json response
	 * It gets the list of asteroids in that range of dates from REST api nasa Neo Feed
	 * It filters the ten that passed closest to earth
	 * @return JSON list of asteroids
	 */
	//Ten asteroids that passed closest to earth between two dates
	public Mono<ServerResponse> tenClosest(ServerRequest request){
		MultiValueMap<String, String> queryParams = request.queryParams();
		return Mono.just(queryParams)
		.map( (MultiValueMap<String, String> optParams) -> {
			String start = optParams.getFirst(paramStartDate);
			String end = optParams.getFirst(paramEndDate);
			if(start.isEmpty()) {
				throw new Error("Error: Param start is empty");				
			}
			if(end.isEmpty()) {
				throw new Error("Error: Param end is empty");				
			}
			else {
				Pair<String, String>  params = new Pair<String, String>(start, end);
				return params;
			}
		})
		.flatMap(params->asteroidService.tenClosest(params.getValue0(), params.getValue1()))
		.flatMap((List<AsteroidClose> asteroidList)->
			ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(fromValue(asteroidList))
		)
		.onErrorResume(this::responseErrors);	
	}
	
	/**
	 * It returns the largest asteroid of that year as REST json response
	 * It gets the list of asteroids of that year from REST api nasa Neo Feed
	 * @return JSON details of largest asteroid
	 */
	public Mono<ServerResponse> largestByYear(ServerRequest request){
		Optional<String> optYear = request.queryParam(paramYear);
		return Mono.just(optYear)
		.map(oyear->{
			if(oyear.isPresent()) {
				return oyear.get();
			}
			else {
				throw new Error("Error: Param year is empty");
			}
		})
		.flatMap(year->asteroidService.largestByYear(year))
		.flatMap((AsteroidLarge asteroid)->
			ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(fromValue(asteroid))
		)
		.onErrorResume(this::responseErrors);
	}
			
}
