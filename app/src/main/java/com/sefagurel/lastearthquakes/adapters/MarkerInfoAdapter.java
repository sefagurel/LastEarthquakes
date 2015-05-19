package com.sefagurel.lastearthquakes.adapters;

import java.util.Date;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.sefagurel.lastearthquakes.R;
import com.sefagurel.lastearthquakes.database.EarthQuakes;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class MarkerInfoAdapter implements InfoWindowAdapter {

	LayoutInflater	inflater	= null;

	TextView		tvLoc, tvMag, tvDepth, tvDate, tvLat, tvLng;

	public MarkerInfoAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public View getInfoContents(Marker marker) {

		View info = inflater.inflate(R.layout.marker_info, null);

		tvLoc = (TextView) info.findViewById(R.id.tv1);
		tvMag = (TextView) info.findViewById(R.id.tv2);
		tvDate = (TextView) info.findViewById(R.id.tv3);
		tvDepth = (TextView) info.findViewById(R.id.tv4);
		tvLat = (TextView) info.findViewById(R.id.tv5);
		tvLng = (TextView) info.findViewById(R.id.tv6);

		String snippet = marker.getSnippet();

		EarthQuakes earthQuakes = new EarthQuakes().getEarthquakesById(Long.parseLong(snippet));

		tvLoc.setText(" : " + earthQuakes.getLocationName());
		tvMag.setText(" : " + earthQuakes.getMagnitude());
		tvDepth.setText(" : " + earthQuakes.getDepth() + " KM");
		tvDate.setText(" : " + new Date(earthQuakes.getDateMilis()).toLocaleString());
		tvLat.setText(" : " + earthQuakes.getLatitude());
		tvLng.setText(" : " + earthQuakes.getLongitude());

		return info;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		// TODO Auto-generated method stub
		return null;
	}

}
