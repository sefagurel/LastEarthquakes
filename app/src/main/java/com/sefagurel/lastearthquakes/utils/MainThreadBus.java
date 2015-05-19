package com.sefagurel.lastearthquakes.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class MainThreadBus extends Bus {

	private final Handler	mHandler	= new Handler(Looper.getMainLooper());

	@Override
	public void post(final Object event) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			super.post(event);
		}
		else {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					MainThreadBus.super.post(event);
				}
			});
		}
	}
}