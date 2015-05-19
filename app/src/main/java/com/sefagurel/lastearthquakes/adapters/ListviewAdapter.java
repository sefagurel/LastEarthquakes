package com.sefagurel.lastearthquakes.adapters;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sefagurel.lastearthquakes.R;
import com.sefagurel.lastearthquakes.database.EarthQuakes;

import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class ListviewAdapter extends ArrayAdapter<EarthQuakes> {

	private List<EarthQuakes>		list;
	private static LayoutInflater	inflater	= null;
	private Activity				act;

	public ListviewAdapter(Activity activity, List<EarthQuakes> datas) {
		super(activity, R.layout.list_row, datas);
		list = datas;
		act = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.list_row, null);

		TextView tvLocation = (TextView) vi.findViewById(R.id.tv_location);
		TextView tvDate = (TextView) vi.findViewById(R.id.tv_date);
		TextView tvDepth = (TextView) vi.findViewById(R.id.tv_depth);
		TextView tvMag = (TextView) vi.findViewById(R.id.tv_mag);

		EarthQuakes earthQuake = list.get(position);

		tvLocation.setText(earthQuake.getLocationName());
		tvDate.setText(": " + new Date(earthQuake.getDateMilis()).toLocaleString());
		tvDepth.setText(": " + Float.toString(earthQuake.getDepth()) + " KM");
		tvMag.setText(Float.toString(earthQuake.getMagnitude()));

		float magnitude = earthQuake.getMagnitude();

		if (magnitude < 3) {
			tvMag.setBackgroundColor(act.getResources().getColor(R.color.COLOR_GREEN));
		}
		else if (magnitude >= 3 && magnitude < 5) {
			tvMag.setBackgroundColor(act.getResources().getColor(R.color.COLOR_YELLOW));
		}
		else if (magnitude >= 5) {
			tvMag.setBackgroundColor(act.getResources().getColor(R.color.COLOR_RED));
		}

		return vi;
	}

	@Override
	public EarthQuakes getItem(int position) {
		return super.getItem(position);
	}

	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	@Override
	public int getPosition(EarthQuakes item) {
		return super.getPosition(item);
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

}