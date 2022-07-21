package com.nasa.client.app.models.json;

public class Kilometers {
	private Double estimated_diameter_max;
	
	public Kilometers(Double em) {
		estimated_diameter_max = em;
	}

	public Double getEstimated_diameter_max() {
		return estimated_diameter_max;
	}

	public void setEstimated_diameter_max(Double estimated_diameter_max) {
		this.estimated_diameter_max = estimated_diameter_max;
	}
}
