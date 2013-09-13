package com.android.phone_market.model.DataBaseOperations;

import com.android.phone_market.model.Constant;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Николай
 * 
 */

public class DataBaseOpenConnection extends SQLiteOpenHelper {

	/**
	 * @param context
	 *            Context
	 * @param DBName
	 *            Название базы данных
	 */

	public DataBaseOpenConnection(Context context, String DBName) {
		super(context, DBName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("create table " + Constant.TABLE_NAME_SAVE_PARAMETERS + " ("
				+ "id integer primary key autoincrement," + "brand text,"
				+ "model text," + "checked_color int," + "checked_state int,"
				+ "checked_region int," + "price text," + "colorCheck text,"
				+ "stateCheck text," + "regionCheck text" + ");");

		db.execSQL("create table "
				+ Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS
				+ " ("
				+ "id integer primary key autoincrement,"
				+ "brand_and_model text,"
				+ "color text,"
				+ "state text,"
				+ "region text,"
				+ "price text,"
				+ "more_info text,"
				+ "name text, phone_number text, other_phone_number text, e_mail text"
				+ ");");
		
		db.execSQL("create table "
				+ Constant.TABLE_NAME_SERVICE_SEARCH_SQL_REQUEST
				+ " ("
				+ "id integer primary key autoincrement,"
				+ "SQL text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * 
		 */
	}
}
