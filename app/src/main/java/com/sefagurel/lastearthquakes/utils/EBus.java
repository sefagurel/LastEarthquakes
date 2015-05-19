package com.sefagurel.lastearthquakes.utils;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class EBus {

	private int	status;

	public EBus(int s) {
		status = s;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
