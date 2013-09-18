package com.Music_Player.music_player.model.MusicExplorer;

import java.util.HashMap;

import com.Music_Player.music_player.model.DataBase.DBConstants;
import com.Music_Player.music_player.model.DataBase.DataBaseOperations;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class MusicExplorer {

	static HashMap<String, Object> track_information;

	public static void UpdateTracksLibryary(Context context) {
		track_information = new HashMap<String, Object>();
		ContentResolver contentResolver = context.getContentResolver();
		Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor == null) {
			// query failed, handle error.
		} else if (!cursor.moveToFirst()) {
			// no media on the device
		} else {
			int titleColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
			int titleAlbum = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.ALBUM);
			int titleArtist = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
			int titleData = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.DATA);

			// 1 - music, 0 - alarm/notif...
			int is_music_flag = cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC);
			do {
				Long is_music = cursor.getLong(is_music_flag);
				String Title = cursor.getString(titleColumn);
				String Album = cursor.getString(titleAlbum);
				String Artist = cursor.getString(titleArtist);
				String Data = cursor.getString(titleData);
				track_information.put(DBConstants.HASH_MAP_KEY_TRACK_TITLE,
						Title);
				track_information.put(DBConstants.HASH_MAP_KEY_ALBUM_TITLE,
						Album);
				track_information.put(DBConstants.HASH_MAP_KEY_ARTIST_TITLE,
						Artist);
				track_information
						.put(DBConstants.HASH_MAP_KEY_TRACK_PATH, Data);
				/*Log.d("Libriary", "Track info:");
				Log.d("Libriary", "Title: " + Title);
				Log.d("Libriary", "Album: " + Album);
				Log.d("Libriary", "Artist: " + Artist);
				Log.d("Libriary", "Data: " + Data);
				Log.d("Libriary", "____________________");
				
				/*
				 * 0 - ID 1 - Data 2 - Title 3 - Album 4 - Artist 5 - Length
				 */
				if (is_music == 1) {
					DataBaseOperations.AddTrackToDB(context, track_information);
				}
			} while ((cursor.moveToNext()));
		}

	}
}