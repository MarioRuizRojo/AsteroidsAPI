package com.nasa.client.app.models;

import com.nasa.client.app.models.json.CAD;
import com.nasa.client.app.models.json.NEO;

/**
 * My object result to send to user
 * @author Mario
 *
 */
public class Asteroid{
	private String neoReferenceId;	
	private String name;
	private Double astronomical;
	private Double magnitude;
	private Double diameter;
	public Asteroid(NEO neo) {
		neoReferenceId=neo.getNeo_reference_id();
		name=neo.getName();
		Double max = Double.MIN_VALUE;
		for(CAD cad : neo.getClose_approach_data()) {
			Double aux = cad.getMiss_distance().getAstronomical();
			if(aux>max) {
				max=aux;
			}
		}
		astronomical=max;
		magnitude=neo.getAbsolute_magnitude_h();
		diameter=neo.getEstimated_diameter().getKilometers().getEstimated_diameter_max();
	}
	public String getNeoReferenceId() {
		return neoReferenceId;
	}
	public void setNeoReferenceId(String neoReferenceId) {
		this.neoReferenceId = neoReferenceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAstronomical() {
		return astronomical;
	}
	public void setAstronomical(Double astronomical) {
		this.astronomical = astronomical;
	}
	public Double getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(Double magnitude) {
		this.magnitude = magnitude;
	}
	public Double getDiameter() {
		return diameter;
	}
	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}
}
