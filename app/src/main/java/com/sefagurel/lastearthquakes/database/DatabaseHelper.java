package com.sefagurel.lastearthquakes.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sefagurel.lastearthquakes.utils.Tools;
import com.sefagurel.lastearthquakes.utils.App;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String		DATABASE_NAME		= "lastearthquakes.db";
	private final Context			myContext;
	private static DatabaseHelper	dbHelper;
	private static final int		DATABASE_VERSION	= 1;
	private static Object			syncObject			= new Object();

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}

	public static DatabaseHelper getDbHelper() {
		synchronized (syncObject) {
			if (dbHelper == null) {
				dbHelper = new DatabaseHelper(App.AppContext);
			}
		}
		return dbHelper;
	}

	private Dao<EarthQuakes, Long>				EarthQuakesDataHelper			= null;
	private Dao<LastEarthquakeDate, Integer>	LastEarthquakeDateDataHelper	= null;

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, EarthQuakes.class);
			TableUtils.createTable(connectionSource, LastEarthquakeDate.class);
		}
		catch (java.sql.SQLException e) {
			Tools.catchException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, EarthQuakes.class, true);
			TableUtils.dropTable(connectionSource, LastEarthquakeDate.class, true);
			onCreate(db, connectionSource);
		}
		catch (java.sql.SQLException e) {
			Tools.catchException(e);
		}
	}

	public void clearDatabase() {
		ConnectionSource connectionSource = getConnectionSource();
		try {
			TableUtils.clearTable(connectionSource, EarthQuakes.class);
		}
		catch (SQLException e) {
			Tools.catchException(e);
		}
	}

	public Dao<EarthQuakes, Long> getEarthQuakesDataHelper() throws SQLException {
		if (EarthQuakesDataHelper == null) {
			EarthQuakesDataHelper = getDao(EarthQuakes.class);
		}
		return EarthQuakesDataHelper;
	}

	public Dao<LastEarthquakeDate, Integer> getLastEarthquakeDateDataHelper() throws SQLException {
		if (LastEarthquakeDateDataHelper == null) {
			LastEarthquakeDateDataHelper = getDao(LastEarthquakeDate.class);
		}
		return LastEarthquakeDateDataHelper;
	}

}
