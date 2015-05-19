package com.sefagurel.lastearthquakes.sources.seismicportal;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class features<S, T> {

	private S		geometry;
	private String	type;
	private String	id;
	private T		properties;

	public S getGeometry() {
		return geometry;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public T getProperties() {
		return properties;
	}

	public void setGeometry(S geometry) {
		this.geometry = geometry;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProperties(T properties) {
		this.properties = properties;
	}

}
