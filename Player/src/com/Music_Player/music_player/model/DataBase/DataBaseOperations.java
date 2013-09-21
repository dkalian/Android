package com.Music_Player.music_player.model.DataBase;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseOperations {
	public static void AddTrackToDB(Context context,
			HashMap<String, Object> track_information) {
		DAO dao = new DAO(context);
		SQLiteDatabase db = dao.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DBConstants.TABLE_TRACKS_NAME_FIELD_TITLE,
				(String) track_information
						.get(DBConstants.HASH_MAP_KEY_TRACK_TITLE));
		cv.put(DBConstants.TABLE_TRACKS_NAME_FIELD_ARTIST,
				(String) track_information
						.get(DBConstants.HASH_MAP_KEY_ARTIST_TITLE));
		cv.put(DBConstants.TABLE_TRACKS_NAME_FIELD_ALBUM,
				(String) track_information
						.get(DBConstants.HASH_MAP_KEY_ALBUM_TITLE));
		cv.put(DBConstants.TABLE_TRACKS_NAME_FIELD_PATH,
				(String) track_information
						.get(DBConstants.HASH_MAP_KEY_TRACK_PATH));
		db.insert(DBConstants.TABLE_TRACKS_NAME, null, cv);
		db.close();
	}

	public static void GetAllTracks(Context context) {
		DAO dao = new DAO(context);
		SQLiteDatabase db = dao.getWritableDatabase();
		Cursor c = db.query(DBConstants.TABLE_TRACKS_NAME, null, null, null,
				null, null, null);
		if (c.moveToFirst()) {
			do {

				int fTitle = c
						.getColumnIndex(DBConstants.TABLE_TRACKS_NAME_FIELD_TITLE);
				int fAlbum = c
						.getColumnIndex(DBConstants.TABLE_TRACKS_NAME_FIELD_ALBUM);
				int fArtist = c
						.getColumnIndex(DBConstants.TABLE_TRACKS_NAME_FIELD_ARTIST);
				int fPath = c
						.getColumnIndex(DBConstants.TABLE_TRACKS_NAME_FIELD_PATH);

				String Title = c.getString(fTitle);
				String Album = c.getString(fAlbum);
				String Artist = c.getString(fArtist);
				String Path = c.getString(fPath);

				Log.d("Libriary", "Track info:");
				Log.d("Libriary", "Title: " + Title);
				Log.d("Libriary", "Album: " + Album);
				Log.d("Libriary", "Artist: " + Artist);
				Log.d("Libriary", "Path: " + Path);
				Log.d("Libriary", "____________________");
			} while (c.moveToNext());
		} else {
			// No data
		}
		db.close();
	}

	public static ArrayList<HashMap<String, Object>> GetAllAlbums(Context context) {
		ArrayList<HashMap<String, Object>> albums = new ArrayList<HashMap<String, Object>>();
		DAO dao = new DAO(context);
		SQLiteDatabase db = dao.getWritableDatabase();
		
		String[] columns = { DBConstants.TABLE_TRACKS_NAME_FIELD_ALBUM }; // выборка
																			// только												// альбомов
		Cursor cursor = db.query(true, DBConstants.TABLE_TRACKS_NAME, columns,
				null, null, null, null, null, null); // выборка без дублирования
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, Object> temp = new HashMap<String, Object>();
				int fAlbum = cursor
						.getColumnIndex(DBConstants.TABLE_TRACKS_NAME_FIELD_ALBUM);
				
				String Album = cursor.getString(fAlbum);
				temp.put(DBConstants.HASH_MAP_KEY_ALBUM_TITLE, Album);
				albums.add(temp);
			} while (cursor.moveToNext());
		} else {
			// No data
		}
		db.close();
		return albums;
	}
}
