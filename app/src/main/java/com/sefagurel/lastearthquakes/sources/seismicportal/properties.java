package com.sefagurel.lastearthquakes.sources.seismicportal;

/**
 * Created by Sefa Gurel on 15.5.2015.
 * sefagurel89@gmail.com
 */
import java.util.Date;

public class properties {

	private Date	lastupdate;
	private String	magtype;
	private String	evtype;
	private double	lon;
	private String	auth;
	private double	lat;
	private float	depth;
	private String	unid;
	private float	mag;
	private Date	time;
	private String	source_id;
	private String	source_catalog;
	private String	flynn_region;

	public Date getLastupdate() {
		return lastupdate;
	}

	public String getMagtype() {
		return magtype;
	}

	public String getEvtype() {
		return evtype;
	}

	public double getLon() {
		return lon;
	}

	public String getAuth() {
		return auth;
	}

	public double getLat() {
		return lat;
	}

	public float getDepth() {
		return depth;
	}

	public String getUnid() {
		return unid;
	}

	public float getMag() {
		return mag;
	}

	public Date getTime() {
		return time;
	}

	public String getSource_id() {
		return source_id;
	}

	public String getSource_catalog() {
		return source_catalog;
	}

	public String getFlynn_region() {
		return flynn_region;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public void setMagtype(String magtype) {
		this.magtype = magtype;
	}

	public void setEvtype(String evtype) {
		this.evtype = evtype;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	public void setMag(float mag) {
		this.mag = mag;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}

	public void setSource_catalog(String source_catalog) {
		this.source_catalog = source_catalog;
	}

	public void setFlynn_region(String flynn_region) {
		this.flynn_region = flynn_region;
	}

}
