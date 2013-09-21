package com.Music_Player.music_player.controller;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.Music_Player.music_player.R;
import com.Music_Player.music_player.model.Adapter.AlbumsAdapter;
import com.Music_Player.music_player.model.DataBase.DataBaseOperations;

/**
 * 
 * @author Nickolay Dubkov
 * @version 0.0.1
 */
public class Artist extends Activity {

	public static final String _ARRTIBUTE_ALBUM_TITLE = "album_title";
	private ListView albumLv;
	Map<String, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artist);
		albumLv = (ListView) findViewById(R.id.activity_artist_album_list);
		albumLv.setAdapter(new AlbumsAdapter(DataBaseOperations
				.GetAllAlbums(this), this));
		albumLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("Clicker", "itemClick: position = " + id);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.artist, menu);
		return true;
	}

	protected void prepareListView() { 
		
	}
	
	protected void loadPictures(String Artist) {
		// Подгрузка 4 случайных картинок
	}
}
