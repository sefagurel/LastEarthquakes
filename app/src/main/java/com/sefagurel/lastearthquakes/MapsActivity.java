package com.sefagurel.lastearthquakes;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sefagurel.lastearthquakes.adapters.MarkerInfoAdapter;
import com.sefagurel.lastearthquakes.database.EarthQuakes;
import com.sefagurel.lastearthquakes.utils.AppSettings;
import com.sefagurel.lastearthquakes.utils.Tools;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

	private GoogleMap			myMap;
	private String				Depth, Lat, Lng, Loc, Mag, Date;
	private MarkerInfoAdapter	infoAdapter;
	private long				itemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		itemId = getIntent().getLongExtra("selectedItem", 0);

		if (itemId == 0) {
			finish();
		}

		setUpMapIfNeeded();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (myMap == null) {
			myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			if (myMap != null) {

				int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

				if (resultCode != ConnectionResult.SUCCESS) {
					Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1);
					dialog.show();
					return;
				}
				else {
					setUpMap();
				}
			}
		}
	}

	private void setUpMap() {

		try {
			int mapType = AppSettings.getInstance().getMapType();

			if (mapType == 0) {
				myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			}
			else if (mapType == 1) {
				myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			}
			else if (mapType == 2) {
				myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			}
			else if (mapType == 3) {
				myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			}

			infoAdapter = new MarkerInfoAdapter(MapsActivity.this.getLayoutInflater());
			myMap.clear();
			myMap.setInfoWindowAdapter(infoAdapter);
			myMap.setMyLocationEnabled(true);
			myMap.setOnMapClickListener(this);
			myMap.setOnMapLongClickListener(this);
			myMap.setOnMarkerClickListener(this);
			myMap.setOnMarkerDragListener(this);

			EarthQuakes currentEarthquakes = new EarthQuakes().getEarthquakesById(itemId);

			for (EarthQuakes earthQuakes : new EarthQuakes().GetAllData()) {

				LatLng position = new LatLng(earthQuakes.getLatitude(), earthQuakes.getLongitude());
				MarkerOptions mOptions = new MarkerOptions();

				mOptions.position(position);
				mOptions.snippet(Long.toString(earthQuakes.getDateMilis()));
				mOptions.visible(true);

				float magnitude = earthQuakes.getMagnitude();

				if (magnitude < 3) {
					mOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				}
				else if (magnitude >= 3 && magnitude < 5) {
					mOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
				}
				else if (magnitude >= 5) {
					mOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				}

				if (earthQuakes.getDateMilis().equals(currentEarthquakes.getDateMilis())) {
					myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 6));
					Marker marker = myMap.addMarker(mOptions);
					marker.showInfoWindow();
				}
				else {
					myMap.addMarker(mOptions);
				}

			}

		}
		catch (Exception e) {
			Tools.catchException(e);
			Toast.makeText(this, getString(R.string.MapCanNotBeDisplayed), Toast.LENGTH_LONG).show();
			finish();
		}

	}

	@Override
	public void onMapClick(LatLng latLng) {

	}

	@Override
	public void onMapLongClick(LatLng latLng) {

	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		return false;
	}

	@Override
	public void onMarkerDragStart(Marker marker) {

	}

	@Override
	public void onMarkerDrag(Marker marker) {

	}

	@Override
	public void onMarkerDragEnd(Marker marker) {

	}

	@Override
	public void onBackPressed() {
		finish();
	}

}
