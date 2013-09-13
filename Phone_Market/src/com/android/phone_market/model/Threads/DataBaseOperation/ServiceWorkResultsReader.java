package com.android.phone_market.model.Threads.DataBaseOperation;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.phone_market.model.Constant;
import com.android.phone_market.model.DataBaseOperations.DataBaseOpenConnection;

/**
 * @author Nickolai
 * 
 */
/*
 * TableName Constant.TABLE_NAME_SAVE_PARAMETERS
 */

public class ServiceWorkResultsReader {
	
	static Cursor c;
	
	/**
	 * Translate ResultSet to ArrayList(ArrayList(String))
	 * @param context Context
	 * @param TableName String
	 * @return ArrayList(ArrayList(String))
	 */
	public static ArrayList<ArrayList<String>> Read(Context context,
			String TableName) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				Constant.LOCAL_DB_NAME);
		SQLiteDatabase db = connection.getWritableDatabase();
		c = db.query(TableName, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			int idColIndex = c.getColumnIndex("id");
			int brand_and_modelColIndex = c.getColumnIndex("brand_and_model");
			int stateColIndex = c.getColumnIndex("state");
			int priceColIndex = c.getColumnIndex("price");
			int regionColIndex = c.getColumnIndex("region");
			int more_infoColIndex = c.getColumnIndex("more_info");
			int nameColIndex = c.getColumnIndex("name");
			int phone_numberColIndex = c.getColumnIndex("phone_number");
			int other_phone_numberColIndex = c
					.getColumnIndex("other_phone_number");
			int e_mailColIndex = c.getColumnIndex("e_mail");
			int colorColIndex = c.getColumnIndex("color");
			do {
				ArrayList<String> tempresults = new ArrayList<String>();

				/*
				 * Основные данные
				 */

				tempresults.add(c.getString(brand_and_modelColIndex)); // 0
				tempresults.add(c.getString(stateColIndex)); // 1
				tempresults.add(c.getString(priceColIndex)); // 2
				tempresults.add(c.getString(regionColIndex)); // 3

				/*
				 * Основные данные
				 */

				/*
				 * Побочные данные
				 */

				tempresults.add(c.getString(idColIndex)); // 4
				tempresults.add(c.getString(more_infoColIndex)); // 5
				tempresults.add(c.getString(nameColIndex)); // 6
				tempresults.add(c.getString(phone_numberColIndex)); // 7
				tempresults.add(c.getString(other_phone_numberColIndex)); // 8
				tempresults.add(c.getString(e_mailColIndex)); // 9
				tempresults.add(c.getString(colorColIndex)); // 10

				/*
				 * Побочные данные
				 */
				results.add(tempresults);
			} while (c.moveToNext());
		} else
			results = null;
		connection.close();
		return results;

	}
}
