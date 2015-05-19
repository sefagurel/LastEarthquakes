package com.sefagurel.lastearthquakes.sources.usgs;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class features1<P, G> {

	private String	type;
	private P		properties;
	private G		geometry;
	private String	id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public P getProperties() {
		return properties;
	}

	public void setProperties(P properties) {
		this.properties = properties;
	}

	public G getGeometry() {
		return geometry;
	}

	public void setGeometry(G geometry) {
		this.geometry = geometry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
