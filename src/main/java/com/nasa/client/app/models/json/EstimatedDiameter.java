package com.nasa.client.app.models.json;

public class EstimatedDiameter {
	private Kilometers kilometers;

	public EstimatedDiameter(Kilometers km) {
		kilometers=km;
	}	
	public Kilometers getKilometers() {
		return kilometers;
	}

	public void setKilometers(Kilometers kilometers) {
		this.kilometers = kilometers;
	}
}
