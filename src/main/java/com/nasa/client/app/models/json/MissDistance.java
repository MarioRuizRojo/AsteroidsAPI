package com.nasa.client.app.models.json;

public class MissDistance {
	private Double astronomical;
	//private Double miles;
	
	public MissDistance(Double as) {
		astronomical=as;
	}

	public Double getAstronomical() {
		return astronomical;
	}

	public void setAstronomical(Double astronomical) {
		this.astronomical = astronomical;
	}
}
