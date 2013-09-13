package com.android.phone_market.model.Threads.DataBaseOperation;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.phone_market.model.Constant;
import com.android.phone_market.model.DataBaseOperations.DataBaseOpenConnection;

/**
 * @author Nickolai
 * 
 */
public class ServiceWorkResultsWriter {

	/**
	 * Write search results to local db
	 * @param context Context
	 * @param TableName String
	 * @param results ArrayList(ArrayList(String))
	 * @return boolean error
	 */
	public static boolean WriteResultsSearch(Context context, String TableName,
			ArrayList<ArrayList<String>> results) {
		int brand_and_model = 0;
		int state = 1;
		int price = 2;
		int region = 3;
		int more_info = 5;
		int name = 6;
		int phone_number = 7;
		int other_phone_number = 8;
		int e_mail = 9;
		int color = 10;

		boolean error = false;
		DataBaseOpenConnection connection = new DataBaseOpenConnection(context,
				Constant.LOCAL_DB_NAME);
		SQLiteDatabase db = connection.getWritableDatabase();
		ContentValues cv = new ContentValues();
		try {
			db.delete(TableName, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < results.size(); i++) {
			cv.put("brand_and_model", results.get(i).get(brand_and_model));
			cv.put("color", results.get(i).get(color));
			cv.put("state", results.get(i).get(state));
			cv.put("more_info", results.get(i).get(more_info));
			cv.put("region", results.get(i).get(region));
			cv.put("price", results.get(i).get(price));
			cv.put("name", results.get(i).get(name));
			cv.put("phone_number", results.get(i).get(phone_number));
			cv.put("other_phone_number", results.get(i).get(other_phone_number));
			cv.put("e_mail", results.get(i).get(e_mail));
			try {
				db.insert(TableName, null, cv);
			} catch (Exception e) {
				e.printStackTrace();
				error = true;
			}
		}
		connection.close();
		return error;
	}
}
