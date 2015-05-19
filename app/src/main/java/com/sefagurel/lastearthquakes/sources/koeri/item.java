package com.sefagurel.lastearthquakes.sources.koeri;

import java.util.Date;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class item {

	private Date	name;
	private String	lokasyon;
	private double	lat;
	private double	lng;
	private float	mag;
	private float	Depth;
	private int		Type;

	public Date getName() {
		return name;
	}

	public String getLokasyon() {
		return lokasyon;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public float getMag() {
		return mag;
	}

	public float getDepth() {
		return Depth;
	}

	public int getType() {
		return Type;
	}

	public void setName(Date name) {
		this.name = name;
	}

	public void setLokasyon(String lokasyon) {
		this.lokasyon = lokasyon;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public void setMag(float mag) {
		this.mag = mag;
	}

	public void setDepth(float depth) {
		Depth = depth;
	}

	public void setType(int type) {
		Type = type;
	}

}
