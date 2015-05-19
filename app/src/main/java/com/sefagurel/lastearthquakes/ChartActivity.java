package com.sefagurel.lastearthquakes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PreviewColumnChartView;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.sefagurel.lastearthquakes.database.EarthQuakes;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */

public class ChartActivity extends AppCompatActivity {

	private ColumnChartView			chart;
	private PreviewColumnChartView	previewChart;
	private ColumnChartData			data;
	private ColumnChartData			previewData;

	Crouton							crouton	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowHomeEnabled(true);
		ab.setIcon(R.mipmap.ic_launcher);
		ab.setTitle("  " + getResources().getString(R.string.app_name));

		ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.statusbar));
		ab.setBackgroundDrawable(cd);

		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowTitleEnabled(true);

		setContentView(R.layout.activity_chart);

		chart = (ColumnChartView) findViewById(R.id.chart);
		chart.setOnValueTouchListener(new ValueTouchListener());
		chart.setOnClickListener(new ValueTouchListener());
		chart.setOnLongClickListener(new ValueTouchListener());
		chart.setOnTouchListener(new ValueTouchListener());

		generateDefaultData();

		chart.setColumnChartData(data);
		chart.setZoomEnabled(true);
		chart.setScrollEnabled(true);

		previewChart = (PreviewColumnChartView) findViewById(R.id.chart_preview);
		previewChart.setColumnChartData(previewData);
		previewChart.setViewportChangeListener(new ViewportListener());

		previewX(true);

	}

	private void generateDefaultData() {

		List<Column> columns = getDatasForChart();

		data = new ColumnChartData(columns);

		Axis axisX = new Axis();
		Axis axisY = new Axis().setHasLines(true);

		axisX.setName(getString(R.string.ChartAxisX));
		axisY.setName(getString(R.string.ChartAxisY));

		data.setAxisXBottom(axisX);
		data.setAxisYLeft(axisY);

		previewData = new ColumnChartData(data);
		for (Column column : previewData.getColumns()) {
			for (SubcolumnValue value : column.getValues()) {
				value.setColor(Color.BLACK);
			}
		}
	}

	private void previewX(boolean animate) {
		Viewport tempViewport = new Viewport(chart.getMaximumViewport());
		float dx = tempViewport.width() / 3f;
		tempViewport.inset(dx, 0);
		if (animate) {
			previewChart.setCurrentViewportWithAnimation(tempViewport);
		}
		else {
			previewChart.setCurrentViewport(tempViewport);
		}
		previewChart.setZoomType(ZoomType.HORIZONTAL);
	}

	public List<Column> getDatasForChart() {

		Calendar cal = Calendar.getInstance();
		// cal.setTimeInMillis(earthQuakes.getDateMilis());
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		List<Column> columns = new ArrayList<>();

		for (int i = 0; i <= 31; ++i) {

			List<EarthQuakes> EarthQuakeList = new EarthQuakes().getEarthquakesByDay(i, month);
			List<SubcolumnValue> subcolumn = new ArrayList<>();

			if (EarthQuakeList.size() < 0) {
				continue;
			}
			for (EarthQuakes earthQuakes : EarthQuakeList) {

				SubcolumnValue subcolumnValue = new SubcolumnValue();

				float magnitude = earthQuakes.getMagnitude();

				if (magnitude < 3) {
					subcolumnValue.setColor(getResources().getColor(R.color.COLOR_GREEN));
				}
				else if (magnitude >= 3 && magnitude < 5) {
					subcolumnValue.setColor(getResources().getColor(R.color.COLOR_YELLOW));
				}
				else if (magnitude >= 5) {
					subcolumnValue.setColor(getResources().getColor(R.color.COLOR_RED));
				}

				subcolumnValue.setValue(earthQuakes.getMagnitude());
				subcolumnValue.setLabel("" + earthQuakes.getDateMilis());

				subcolumn.add(subcolumnValue);
			}

			columns.add(new Column(subcolumn));
		}

		return columns;
	}

	private void previewY() {
		Viewport tempViewport = new Viewport(chart.getMaximumViewport());
		float dy = tempViewport.height() / 4;
		tempViewport.inset(0, dy);
		previewChart.setCurrentViewportWithAnimation(tempViewport);
		previewChart.setZoomType(ZoomType.VERTICAL);
	}

	private void previewXY() {
		Viewport tempViewport = new Viewport(chart.getMaximumViewport());
		float dx = tempViewport.width() / 4;
		float dy = tempViewport.height() / 4;
		tempViewport.inset(dx, dy);
		previewChart.setCurrentViewportWithAnimation(tempViewport);
	}

	private class ViewportListener implements ViewportChangeListener {
		@Override
		public void onViewportChanged(Viewport newViewport) {
			chart.setCurrentViewport(newViewport);
		}

	}

	private class ValueTouchListener implements LineChartOnValueSelectListener, ColumnChartOnValueSelectListener, View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

		@Override
		public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
		}

		@Override
		public void onValueDeselected() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {

			String str = String.valueOf(subcolumnValue.getLabel());

			EarthQuakes earthQuakes = new EarthQuakes().getEarthquakesById(Long.parseLong(str));

			Style.Builder sBuilder = new Style.Builder();
			sBuilder.setGravity(Gravity.LEFT);

			float magnitude = earthQuakes.getMagnitude();

			if (magnitude < 3) {
				sBuilder.setBackgroundColorValue(getResources().getColor(R.color.COLOR_GREEN));
			}
			else if (magnitude >= 3 && magnitude < 5) {
				sBuilder.setBackgroundColorValue(getResources().getColor(R.color.COLOR_YELLOW));
			}
			else if (magnitude >= 5) {
				sBuilder.setBackgroundColorValue(getResources().getColor(R.color.COLOR_RED));
			}

			String message = "";

			if (getString(R.string.Location).equalsIgnoreCase("Konum")) {
				message = " " + getString(R.string.Magnitude) + "   :  " + earthQuakes.getMagnitude() + "\n" //
						+ " " + getString(R.string.Location) + "  :  " + earthQuakes.getLocationName() + "\n" //
						+ " " + getString(R.string.Date) + "     :  " + new Date(earthQuakes.getDateMilis()).toLocaleString();
			}
			else {
				message = " " + getString(R.string.Magnitude) + "  :  " + earthQuakes.getMagnitude() + "\n" //
						+ " " + getString(R.string.Location) + "     :  " + earthQuakes.getLocationName() + "\n" //
						+ " " + getString(R.string.Date) + "            :  " + new Date(earthQuakes.getDateMilis()).toLocaleString();
			}

			if (crouton == null) {
				crouton = Crouton.makeText(ChartActivity.this, message, sBuilder.build());
				crouton.show();
			}
			else {
				crouton.hide();
				crouton = Crouton.makeText(ChartActivity.this, message, sBuilder.build());
				crouton.show();
			}

		}

		@Override
		public void onClick(View v) {

		}

		@Override
		public boolean onLongClick(View v) {
			return false;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return false;
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	protected void onDestroy() {
		Crouton.cancelAllCroutons();
		super.onDestroy();
	}
}
