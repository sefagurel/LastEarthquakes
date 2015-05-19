package com.sefagurel.lastearthquakes.sources.seismicportal;

import java.util.List;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class geometry {

	private String		type;
	private List<Float>	coordinates;

	public String getType() {
		return type;
	}

	public List<Float> getCoordinates() {
		return coordinates;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCoordinates(List<Float> coordinates) {
		this.coordinates = coordinates;
	}

}
