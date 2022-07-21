package com.nasa.client.app.models.dao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nasa.client.app.models.documents.DayNeos;
/**
 * 
 * @author Mario Ruiz Rojo
 * MongoDB adapter to NeoFeedResult collection
 */
public interface DayNeosDao extends ReactiveMongoRepository<DayNeos, String>{

}
