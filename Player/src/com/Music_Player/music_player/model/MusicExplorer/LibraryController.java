package com.Music_Player.music_player.model.MusicExplorer;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class LibraryController {

	private ArrayList<HashMap<String, Object>> all_tracks_info;

	/**
	 * 
	 * @param context Context
	 * @return information about all track <br>
	 * all tracks info [HashMap<String, Object>]
	 * 
	 */
	
	public ArrayList<HashMap<String, Object>> getAllMusic(Context context) {

		all_tracks_info = new ArrayList<HashMap<String, Object>>();

		ContentResolver contentResolver = context.getContentResolver();
		Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor == null) {
			// query failed, handle error.
		} else if (!cursor.moveToFirst()) {
			// no media on the device
		} else {
			int idColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
			int titleColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
			int titleAlbum = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.ALBUM);
			int titleArtist = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
			int titleData = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.DATA);

			// 1 - if music
			int is_music_flag = cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC);

			do {
				HashMap<String, Object> track_info = new HashMap<String, Object>();

				//long Id = cursor.getLong(idColumn);
				String Title = cursor.getString(titleColumn);
				String Album = cursor.getString(titleAlbum);
				String Artist = cursor.getString(titleArtist);
				String Data = cursor.getString(titleData);

				track_info.put(Constant.HASH_MAP_KEY_TRACK_PATH, Data);
				track_info.put(Constant.HASH_MAP_KEY_TRACK_TITLE, Title);
				track_info.put(Constant.HASH_MAP_KEY_ALBUM_TITLE, Album);
				track_info.put(Constant.HASH_MAP_KEY_ARTIST_TITLE, Artist);

				if (is_music_flag == 1) {
					all_tracks_info.add(track_info);
				}
			} while ((cursor.moveToNext()));
		}

		return all_tracks_info;
	}
}
