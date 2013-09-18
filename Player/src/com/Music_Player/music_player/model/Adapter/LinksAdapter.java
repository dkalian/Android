/**
 * 
 */
package com.Music_Player.music_player.model.Adapter;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.Music_Player.music_player.R;
import com.Music_Player.music_player.model.DataBase.DAO;
import com.Music_Player.music_player.model.DataBase.DBConstants;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Doc
 * 
 */
/**
 * Наследуемся от BaseAdapter: результат экземпляр нашего класса и будет
 * адаптеро для ListView реализуем интерфейс Filterable для фильтрации нашего
 * адаптера.
 **/
public class LinksAdapter extends BaseAdapter implements Filterable {
	private Context context;

	/** константы определяют наши textView для вывода данных **/
	private static final int ALBUM_TITLE = R.id.item_album_album_title_text;
	private static final int RESOURCE = R.id.activity_artist_album_list;
		
	private ImageButton buttonPlay;

	private TextView album_title;

	/**
	 * @param context
	 * @param dataMap
	 *            Default конструктор передаем данные в виде
	 *            List<HashMap<String, String>> dataMap и контекст приложения
	 */
	public LinksAdapter(Context context) {
		this.context = context;
	}

	/**
	 * ========================================================================
	 * ============= Создаем фильтр, заполняем его данныими ===================
	 * ========================================================================
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			// создаем view элемент из нашего слоя
			LayoutInflater inflater = LayoutInflater.from(context);
			v = inflater.inflate(RESOURCE, null);

		}
		album_title = (TextView) v.findViewById(ALBUM_TITLE);
		
		DAO dao = new DAO(context);
		SQLiteDatabase db = dao.getWritableDatabase();
		Cursor c = db.query(DBConstants.TABLE_TRACKS_NAME, null, null, null,
				null, null, null);
		db.close();
		album_title.setText("1");

		buttonPlay = (ImageButton) v.findViewById(R.id.item_album_button_play_album);
		buttonPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Album play", Toast.LENGTH_LONG).show();
			}
		});
		return v;
	}

	
	
	/**
	 * ========================================================================
	 * ========================== Работа с фильтром ===========================
	 * ========================================================================
	 */
/*
	@Override
	public Filter getFilter() {
		return new Filter() {
			private int oldCharSequenceLegth;
			private String oldidkeyPress;

			@Override
			protected FilterResults performFiltering(CharSequence charSequence) {
				// создаем новый список с результатами фильтрации
				ArrayList<HashMap<String, Object>> filterResultsData = new ArrayList<HashMap<String, Object>>();
				if (getID_KEY_PRESSED().equals(ConstantsLinks.ALL) || getID_KEY_PRESSED() != oldidkeyPress) {
					dataMap = originalData;
				} else {
					if (charSequence.length() < oldCharSequenceLegth) {
						dataMap = originalStateData;
					}
				}
				oldidkeyPress = getID_KEY_PRESSED();
				oldCharSequenceLegth = charSequence.length();
				FilterResults results = new FilterResults();
				// --------- <Блок обработки нажатия фильтр-кнопок> ---------
				// если была нажата любая клавиша сортировки по STATE
				if (!getID_KEY_PRESSED().equals(ConstantsLinks.ALL)) {
					// пробегаемся иттератором по всем элементам списка
					for (HashMap<String, Object> data : dataMap) {
						// выбираем ссылки с STATE = getID_KEY_PRESSED()
						if (String.valueOf(data.get(ConstantsLinks.STATUS)).toUpperCase(Locale.ENGLISH)
								.startsWith(getID_KEY_PRESSED().toString().toUpperCase(Locale.ENGLISH))) {
							filterResultsData.add(data);
						}
					}
					// сохраняем результат поиска для востановления в случае
					// если charSequence.length() < oldCharSequenceLegth
					originalStateData = new ArrayList<HashMap<String, Object>>(filterResultsData);
					// сохраняем результат поиска для передачи в следующий цикл
					dataMap = new ArrayList<HashMap<String, Object>>(filterResultsData);
					filterResultsData.clear();
				}

				// -----------<Блок обрабоки поиска по вхождению > ----------

				if (charSequence != null && charSequence.toString().length() > 0) {
					for (HashMap<String, Object> data : dataMap) {
						// задаем критерии поиска, в данном случае я ищу
						// вхождения только по одному полю URL
						if (String.valueOf(data.get(ConstantsLinks.TXT)).toUpperCase(Locale.ENGLISH)
								.startsWith(charSequence.toString().toUpperCase(Locale.ENGLISH))) {
							filterResultsData.add(data);
						}
					}
					// загоняем результат поиска
					results.values = filterResultsData;
					results.count = filterResultsData.size();
				} else {
					results.values = dataMap;
					results.count = dataMap.size();
				}
				return results;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
				dataMap = (ArrayList<HashMap<String, Object>>) filterResults.values;
				// сообщаем о том что изменился наш список данных
				notifyDataSetChanged();
			}
		};
	}
*/
	/**
	 * ========================================================================
	 * ========================== AsyncTask обработка ссылки ==================
	 * ========================================================================
	 */
/*
	private class SetLinkPlacementAccept extends AsyncTask<Long, Void, Boolean> {
		SetPlacementsAcceptWm setPlace;
		private static final int LINKS_ID = 0;

		@Override
		protected Boolean doInBackground(Long... params) {
			setPlace = new SetPlacementsAcceptWm();
			boolean result = setPlace.setPlacementsAcceptWmForOneLinks(params[LINKS_ID]);
			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				Log.i(null, context.getResources().getString(R.string.accept_Links));
				// TODO Удаляем эту запись из оригинального масиива данных...
			} else {
				Log.i(null, context.getResources().getString(R.string.accept_error));
			}

		}
	}
*/
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
