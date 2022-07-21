package com.nasa.client.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nasa.client.app.models.json.NeoFeedResult;

import reactor.core.publisher.Mono;
/**
 * 
 * @author Mario Ruiz Rojo
 * REST api products request functions
 */
@Service
public class NasaServiceImpl implements NasaService{
	
	@Value("${API_KEY}")
    private String apiKey;

	@Value("${config.paramStartDate}")
	private String paramStartDate;
	
	@Value("${config.paramEndDate}")
	private String paramEndDate;
	
	/**
	 * REST api products
	 */
	@Autowired
	private WebClient client;
	
	//https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
	//https://api.nasa.gov/neo/rest/v1/feed?start_date=1995-02-01&end_date=1996-01-05&api_key=API_KEY
	/**
	 * GET list asteroids inside date range
	 * Date format YYYY-MM-DD
	 * It converts JSON response into Near Earth Object Feed API result object
	 */
	@Override
	public Mono<NeoFeedResult> neoFeed(String start, String end) {
		return client.get()
		.uri(uriBuilder -> uriBuilder
		    .queryParam(paramStartDate, start)
		    .queryParam(paramEndDate, end)
		    .queryParam("api_key", apiKey)//TODO: set this as default param
		    .build())
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.onStatus(status -> status.value() == 429, error -> {
			String waitTime = error.headers().header("Retry-after").get(0);
			int seconds = Integer.parseInt(waitTime);
			return Mono.error(new Exception("Please wait: "+seconds));
		})
		.bodyToMono(NeoFeedResult.class);
	}
}
