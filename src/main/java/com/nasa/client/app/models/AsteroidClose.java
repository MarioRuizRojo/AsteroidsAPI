package com.nasa.client.app.models;

import com.nasa.client.app.models.json.NEO;

public class AsteroidClose extends Asteroid implements Comparable<AsteroidClose>{

	public AsteroidClose(NEO neo) {
		super(neo);		
	}
	@Override
	public int compareTo(AsteroidClose o) {
		return this.getAstronomical().compareTo(o.getAstronomical());
	}	
	
}
