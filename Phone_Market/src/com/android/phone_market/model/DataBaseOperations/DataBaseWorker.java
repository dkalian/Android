package com.android.phone_market.model.DataBaseOperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.phone_market.model.Constant;

/**
 * @author Николай
 * 
 */
public class DataBaseWorker {
	String LOG_TAG = "Data Base Worker: ";
	Context context;

	/**
	 * @param context
	 *            Activity context
	 */
	public DataBaseWorker(Context context) {
		this.context = context;
	}

	Cursor c = null;
	ContentValues results = new ContentValues();

	/**
	 * @param DataBaseName DB Name
	 * @param TableName Table Name
	 * @return content values
	 */
	public ContentValues CheckForSavedRecords(String DataBaseName,
			String TableName) {
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				DataBaseName);
		SQLiteDatabase db = connection.getWritableDatabase();

		try {
			c = db.query(TableName, null, null, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((c != null) && (c.moveToFirst())) {

			int brandColIndex = c.getColumnIndex("brand");
			int modelColIndex = c.getColumnIndex("model");
			int checkedColorIndex = c.getColumnIndex("checked_color");
			int checkedStateIndex = c.getColumnIndex("checked_state");
			int priceIndex = c.getColumnIndex("price");
			int checkedRegionIndex = c.getColumnIndex("checked_region");
			int colorCheckedIndex = c.getColumnIndex("colorCheck");
			int stateCheckedIndex = c.getColumnIndex("stateCheck");
			int regionCheckedIndex = c.getColumnIndex("regionCheck");
			results.put("brand", c.getString(brandColIndex));
			results.put("model", c.getString(modelColIndex));
			results.put("checkedColor", c.getInt(checkedColorIndex));
			results.put("checkedState", c.getInt(checkedStateIndex));
			results.put("price", c.getString(priceIndex));
			results.put("checkedRegion", c.getInt(checkedRegionIndex));
			results.put("colorCheck", c.getInt(colorCheckedIndex));
			results.put("stateCheck", c.getInt(stateCheckedIndex));
			results.put("regionCheck", c.getInt(regionCheckedIndex));
			db.delete(TableName, null, null);
		} else
			Log.d(LOG_TAG, "0 rows");

		connection.close();
		return results;
	}

	/**
	 * Delete All Data in table
	 */
	public void DeleteSavedRecords() {
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				Constant.LOCAL_DB_NAME);
		SQLiteDatabase db = connection.getWritableDatabase();
		db.delete(Constant.TABLE_NAME_SAVE_PARAMETERS, null, null);

	}

	/**
	 * Save SQL request in DB
	 * @param SQL SQL request
	 */
	public void SaveSQLRequest(String SQL) {
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				Constant.LOCAL_DB_NAME);
		SQLiteDatabase db = connection.getWritableDatabase();
		try {
			db.delete(Constant.TABLE_NAME_SERVICE_SEARCH_SQL_REQUEST, null,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ContentValues cv = new ContentValues();
		cv.put("SQL", SQL);
		db.insert(Constant.TABLE_NAME_SERVICE_SEARCH_SQL_REQUEST, null, cv);
		connection.close();
	}

	/**
	 * Return SQL request
	 * @return String SQL request
	 */
	public String GetSavedSQL() {
		String SQL = null;
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				Constant.LOCAL_DB_NAME);
		SQLiteDatabase db = connection.getWritableDatabase();
		try {
			c = db.query(Constant.TABLE_NAME_SERVICE_SEARCH_SQL_REQUEST, null,
					null, null, null, null, null);
		} catch (Exception e) {
			Log.d("Data Base Worker:", "Cursor set error");
			Log.d("Data Base Worker:", "Start stacktrace");
			e.printStackTrace();
			Log.d("Data Base Worker:", "End stacktrace");
			Log.d("Data Base Worker:", "Cursor set error");
		}
		int SQLIndex = c.getColumnIndex("SQL");
		c.moveToFirst();
		SQL = c.getString(SQLIndex);
		connection.close();
		return SQL;
	}
	
	/**
	 * Backup search parameters in local data base
	 * @param brand String
	 * @param model String
	 * @param checked_color int
	 * @param checked_state int
	 * @param checked_region int 
	 * @param price String price
	 * @param colorCheck boolean
	 * @param stateCheck boolean
	 * @param regionCheck boolean 
	 */
	public void SaveSearchParameters(String brand, String model,
			int checked_color, int checked_state, int checked_region,
			String price, boolean colorCheck, boolean stateCheck,
			boolean regionCheck) {
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				Constant.LOCAL_DB_NAME);
		SQLiteDatabase db = connection.getWritableDatabase();
		ClearTable(db, Constant.TABLE_NAME_SAVE_PARAMETERS);
		ContentValues cv = new ContentValues();
		cv.put("brand", brand);
		cv.put("model", model);
		cv.put("checked_color", checked_color);
		cv.put("checked_state", checked_state);
		cv.put("checked_region", checked_region);
		cv.put("price", price);
		cv.put("colorCheck", colorCheck);
		cv.put("stateCheck", stateCheck);
		cv.put("regionCheck", regionCheck);
		long rowID = db.insert(Constant.TABLE_NAME_SAVE_PARAMETERS, null, cv);
		Log.d(LOG_TAG, "row inserted, ID = " + rowID);
		db.close();
	}

	/**
	 * Delete all data in table
	 * @param db SQLiteDatabase
	 * @param TableName String
	 */
	public void ClearTable(SQLiteDatabase db, String TableName) {
		try {
			int clearCount = db.delete(TableName, null, null);
			Log.d(LOG_TAG, "deleted rows count = " + clearCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
