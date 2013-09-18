package com.Music_Player.music_player.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.Music_Player.music_player.R;

/**
 * 
 * @author Nickolay Dubkov
 * @version 0.0.1
 */
public class Album extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.album, menu);
		return true;
	}

}
