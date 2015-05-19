package com.sefagurel.lastearthquakes.sources.usgs;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class metadata1 {

	private long	generated;
	private String	url;
	private String	subTitle;
	private int		cacheMaxAge;

	public long getGenerated() {
		return generated;
	}

	public void setGenerated(long generated) {
		this.generated = generated;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getCacheMaxAge() {
		return cacheMaxAge;
	}

	public void setCacheMaxAge(int cacheMaxAge) {
		this.cacheMaxAge = cacheMaxAge;
	}

}
