package com.nasa.client.app.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="dayneos")
public class DayNeos {
	
	@Id
	private String day;
	
	/**
	 * List of NEOs in JSON format
	 */
	private String json;
	
	public DayNeos() {
		super();
	}
	
	public DayNeos(String d, String js) {
		super();
		day=d;
		json=js;
	}
	
	public String getJson() {
		return json;
	}
	
	public void setJson(String json) {
		this.json = json;
	}
}
