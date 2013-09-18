package com.Music_Player.music_player.controller;

import com.Music_Player.music_player.R;
import com.Music_Player.music_player.R.layout;
import com.Music_Player.music_player.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * 
 * @author Nickolay Dubkov
 * @version 0.0.1
 */
public class Track extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.track, menu);
		return true;
	}

}
