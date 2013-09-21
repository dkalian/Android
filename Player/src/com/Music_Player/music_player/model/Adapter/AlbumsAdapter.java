package com.Music_Player.music_player.model.Adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Music_Player.music_player.R;
import com.Music_Player.music_player.model.DataBase.DBConstants;

/**
 * @author Doc
 * @version 0.0.1
 */
public class AlbumsAdapter extends BaseAdapter {

	/** константы определ€ют наши textView дл€ вывода данных **/
	private static final int ALBUM_TITLE = R.id.item_album_album_title_text;
	private static final int BUTTON_PLAY_ALBUM = R.id.item_album_button_play_album;
	private static final int BUTTON_ADD_TO_PLAYLIST = R.id.item_album_button_add_to_playlist;

	private static final int RESOURCE = R.layout.item_album;

	private Context context;
	private ArrayList<HashMap<String, Object>> albumsList;

	/**
	 * јдаптер дл€ списка: сайты в акаунте
	 * 
	 * @param sitesList
	 * @param context
	 */
	public AlbumsAdapter(ArrayList<HashMap<String, Object>> albumsList,
			Context context) {
		this.context = context;
		this.albumsList = albumsList;
	}

	@Override
	public int getCount() {
		return albumsList.size();
	}

	@Override
	public Object getItem(int position) {
		return albumsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private TextView textalbum;
	private ImageButton buttonPlayAlbum;
	private ImageButton buttonAddToPlaylist;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			v = inflater.inflate(RESOURCE, null);

		}
		textalbum = (TextView) v.findViewById(ALBUM_TITLE);
		buttonPlayAlbum = (ImageButton) v.findViewById(BUTTON_PLAY_ALBUM);
		buttonAddToPlaylist = (ImageButton) v.findViewById(BUTTON_PLAY_ALBUM);

		HashMap<String, Object> albumsMap = new HashMap<String, Object>();
		albumsMap = (HashMap<String, Object>) albumsList.get(position); 
		buttonPlayAlbum.setFocusable(false);
		//buttonAddToPlaylist.setFocusable(false);
		textalbum.setText(albumsMap.get(DBConstants.HASH_MAP_KEY_ALBUM_TITLE)
				.toString());

		buttonPlayAlbum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LinearLayout layout = (LinearLayout) v.getParent();
				TextView albumTitle = (TextView) layout
						.findViewById(ALBUM_TITLE);
				Log.d(null, albumTitle.getText().toString());
			}
		});

		buttonAddToPlaylist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout layout = (LinearLayout) v.getParent();
				TextView albumTitle = (TextView) layout
						.findViewById(ALBUM_TITLE);
				Toast.makeText(context, albumTitle.getText()+" added to playlist", Toast.LENGTH_SHORT).show();
			}
		});
		
		return v;
	}
}
