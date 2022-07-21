package com.nasa.client.app.models;

import com.nasa.client.app.models.json.NEO;

public class AsteroidLarge extends Asteroid implements Comparable<AsteroidLarge>{

	public AsteroidLarge(NEO neo) {
		super(neo);		
	}

	@Override
	public int compareTo(AsteroidLarge o) {
		return this.getDiameter().compareTo(o.getDiameter());
	}	
}
