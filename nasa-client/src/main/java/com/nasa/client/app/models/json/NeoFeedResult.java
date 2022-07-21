package com.nasa.client.app.models.json;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Mario Ruiz Rojo
 * Object wrapper for JSON mapping
 * NeoFeedResult = {'near_earth_objects':{'1995-02-08':[],'1995-02-06':[],...} };
 */
public class NeoFeedResult {
	
	private Integer element_count;
	private Map<String, List<NEO>> near_earth_objects;
	
	public NeoFeedResult(Integer ec, Map<String, List<NEO>> neo) {
		element_count=ec;
		near_earth_objects=neo;
	}
	
	public Integer getElement_count() {
		return element_count;
	}
	public void setElement_count(Integer element_count) {
		this.element_count = element_count;
	}
	public Map<String, List<NEO>> getNear_earth_objects() {
		return near_earth_objects;
	}
	public void setNear_earth_objects(Map<String, List<NEO>> near_earth_objects) {
		this.near_earth_objects = near_earth_objects;
	}
}
