package com.nasa.client.app.models.json;

import java.util.List;

/**
 * Near Earth Object
 * @author Mario
 *
 */
public class NEO {		
	private String neo_reference_id;
	private String name;	
	private Double absolute_magnitude_h;
	private EstimatedDiameter estimated_diameter;
	private List<CAD> close_approach_data;
	
	public NEO(String nri, String nam, Double amh, EstimatedDiameter ed, List<CAD> cads) {
		neo_reference_id=nri;
		name=nam;
		absolute_magnitude_h=amh;
		estimated_diameter=ed;
		close_approach_data=cads;
	}
	
	public List<CAD> getClose_approach_data() {
		return close_approach_data;
	}
	public void setClose_approach_data(List<CAD> close_approach_data) {
		this.close_approach_data = close_approach_data;
	}
	public Double getAbsolute_magnitude_h() {
		return absolute_magnitude_h;
	}
	public void setAbsolute_magnitude_h(Double absolute_magnitude_h) {
		this.absolute_magnitude_h = absolute_magnitude_h;
	}
	public String getNeo_reference_id() {
		return neo_reference_id;
	}
	public void setNeo_reference_id(String neo_reference_id) {
		this.neo_reference_id = neo_reference_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EstimatedDiameter getEstimated_diameter() {
		return estimated_diameter;
	}
	public void setEstimated_diameter(EstimatedDiameter estimated_diameter) {
		this.estimated_diameter = estimated_diameter;
	}
}
