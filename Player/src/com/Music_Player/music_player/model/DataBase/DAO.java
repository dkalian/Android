/**
 * 
 */
package com.Music_Player.music_player.model.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Nickolay Dubkov
 * @version 0.0.1
 */
public class DAO extends SQLiteOpenHelper {
	/**
	 * 
	 * @param context
	 *            Context
	 * @param name
	 *            DBName version DB 1
	 */
	public DAO(Context context) {
		super(context, DBConstants.DBName, null, 1);

	}
/* TODO БАЗА ДАННЫХ!!!
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "
				+ Constant.TABLE_TRACKS_NAME
				+ " ("
				+ "id integer primary key autoincrement, id_artist integer references "
				+ Constant.TABLE_ARTIST_NAME
				+ " (id), id_album, path text, title text);");
		db.execSQL("create table " + Constant.TABLE_ALBUMS_NAME + " ("
				+ "id integer primary key autoincrement references "
				+ Constant.TABLE_TRACKS_NAME
				+ " (id_album), id_artist integer, title text, year text);");
		// TODO Дописать запрос
		db.execSQL("create table " + Constant.TABLE_ARTIST_NAME + " ("
				+ "id integer primary key autoincrement references "
				+ Constant.TABLE_TRACKS_NAME + " (id_artist)"
				+ ", title text);");
	
	}*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table "
				+ DBConstants.TABLE_TRACKS_NAME
				+ " ("
				+ "id integer primary key autoincrement, title text, album text, artist text, path text);");
	}


}
