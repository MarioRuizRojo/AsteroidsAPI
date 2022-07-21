package com.nasa.client.app.models.json;

/**
 * Close Approach Data
 * @author Mario
 *
 */
public class CAD {
	private MissDistance miss_distance;
	
	public CAD(MissDistance md) {
		miss_distance=md;
	}

	public MissDistance getMiss_distance() {
		return miss_distance;
	}

	public void setMiss_distance(MissDistance miss_distance) {
		this.miss_distance = miss_distance;
	}
}
