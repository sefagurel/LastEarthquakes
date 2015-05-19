package com.sefagurel.lastearthquakes.sources.usgs;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
import java.util.List;

public class geometry1 {

	private String		type;
	private List<Float>	coordinates;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Float> coordinates) {
		this.coordinates = coordinates;
	}

}
