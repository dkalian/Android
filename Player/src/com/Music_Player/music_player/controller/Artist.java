package com.Music_Player.music_player.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.Music_Player.music_player.R;
import com.Music_Player.music_player.model.DataBase.DataBaseOperations;
import com.Music_Player.music_player.model.MusicExplorer.MusicExplorer;

/**
 * 
 * @author Nickolay Dubkov
 * @version 0.0.1
 */
public class Artist extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artist);
		MusicExplorer.UpdateTracksLibryary(this);
		DataBaseOperations.GetAllTracks(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.artist, menu);
		return true;
	}

}
