package com.sefagurel.lastearthquakes.database;

import java.sql.SQLException;
import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.lastearthquakes.utils.Tools;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */

@DatabaseTable(tableName = "LastEarthquakeDate")
public class LastEarthquakeDate implements Parcelable, Comparator<LastEarthquakeDate> {

	@DatabaseField(id = true) private int	id;
	@DatabaseField private Long				DateMilis;

	public LastEarthquakeDate() {
		this.id = 1;
	}

	public void Insert() {

		try {
			Dao<LastEarthquakeDate, Integer> Missionsinsert = (DatabaseHelper.getDbHelper()).getLastEarthquakeDateDataHelper();
			LastEarthquakeDate existenceCheck = Missionsinsert.queryForId(this.id);

			if (existenceCheck != null) {
				Missionsinsert.update(this);
			}
			else {
				Missionsinsert.create(this);
			}

		}
		catch (SQLException e) {
			Tools.catchException(e);
		}
	}

	public Long GetLastEarthquakeMilisDate() {
		LastEarthquakeDate lastDate = null;
		try {
			Dao<LastEarthquakeDate, Integer> dao = DatabaseHelper.getDbHelper().getLastEarthquakeDateDataHelper();
			lastDate = dao.queryForId(1);
		}
		catch (Exception e) {
			Tools.catchException(e);
		}
		return lastDate.getDateMilis();
	}

	public int GetRowCount() {
		int count = 0;

		try {
			Dao<LastEarthquakeDate, Integer> dao = DatabaseHelper.getDbHelper().getLastEarthquakeDateDataHelper();
			count = (int) dao.countOf();
		}
		catch (Exception e) {
			Tools.catchException(e);
		}

		return count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getDateMilis() {
		return DateMilis;
	}

	public void setDateMilis(Long dateMilis) {
		DateMilis = dateMilis;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compare(LastEarthquakeDate lhs, LastEarthquakeDate rhs) {
		return (int) (lhs.DateMilis - rhs.DateMilis);
	}

}
