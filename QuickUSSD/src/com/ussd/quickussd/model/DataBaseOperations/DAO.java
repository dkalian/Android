package com.ussd.quickussd.model.DataBaseOperations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Nickolai
 * 
 */
public class DAO extends SQLiteOpenHelper {
	/**
	 * 
	 * @param context
	 *            Context
	 */
	public DAO(Context context) {
		super(context, DataBaseConstant.DBNAME, null, 1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + DataBaseConstant.TABLE_CARDS_INFO_NAME
				+ " (" + "id integer primary key autoincrement,"
				+ DataBaseConstant.TABLE_CARDS_INFO_FIELD_BANK_NAME + " text, "
				+ DataBaseConstant.TABLE_CARDS_INFO_FIELD_ABOUT_CARD_NAME + " text,"
				+ DataBaseConstant.TABLE_CARDS_INFO_FIELD_KEEPER_NAME
				+ " text,"
				+ DataBaseConstant.TABLE_CARDS_INFO_FIELD_ACCOUNT_NAME
				+ "  integer, "
				+ DataBaseConstant.TABLE_CARDS_INFO_FIELD_ACTUAL_DATE_NAME
				+ " text);");
	}

}
