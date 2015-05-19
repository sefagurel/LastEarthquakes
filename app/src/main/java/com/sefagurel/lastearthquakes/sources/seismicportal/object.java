package com.sefagurel.lastearthquakes.sources.seismicportal;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class object<S, T> {

	private String	type;
	private S		metadata;
	private T		features;

	public String getType() {
		return type;
	}

	public S getMetadata() {
		return metadata;
	}

	public T getFeatures() {
		return features;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMetadata(S metadata) {
		this.metadata = metadata;
	}

	public void setFeatures(T features) {
		this.features = features;
	}

}
