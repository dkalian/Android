package com.android.phone_market.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.phone_market.model.Constant;
import com.android.phone_market.model.Dialogs.MyAlertDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author Николай
 * 
 */
public class ResultsSearchActivity extends Activity {
	final String ATTRIBUTE_BRAND_AND_MODEL_TEXT = "brand_and_model";
	final String ATTRIBUTE_STATE_TEXT = "state";
	final String ATTRIBUTE_PRICE_TEXT = "price";
	final String ATTRIBUTE_REGION_TEXT = "region";
	final String ATTRIBUTE_NAME_IMAGE = "image";
	ListView lvSimple;
	SearchPhoneActivity searchActivity;
	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results_search);
		Bundle extras = getIntent().getExtras();
		if (extras == null)
			MyAlertDialog.Show(this, "Ошибка",
					"При открытии результата возникла ошибка.");
		else {
			results = (ArrayList<ArrayList<String>>) extras.get("results");
			if (results != null) {
				PaintList(results);
			} else {
				MyAlertDialog.Show(this, "Ошибка", "Результат пустой");
			}
		}
	}

	protected void PaintList(ArrayList<ArrayList<String>> results) {
		int img = R.drawable.no_image; // Картинка
		if (results.size() != 0) {
			System.out.println(results);
			ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
					results.size());
			Map<String, Object> map;
			int brand = 0;
			int state = 1;
			int price = 2;
			int region = 3;
			for (int col = 0; col < results.size(); col++) {
				map = new HashMap<String, Object>();
				map.put(ATTRIBUTE_BRAND_AND_MODEL_TEXT,
						results.get(col).get(brand));
				map.put(ATTRIBUTE_STATE_TEXT,
						"Сост.: " + results.get(col).get(state));
				map.put(ATTRIBUTE_PRICE_TEXT, results.get(col).get(price)
						+ " $");
				if (results.get(col).get(region).length() != 0) {
					map.put(ATTRIBUTE_REGION_TEXT, "Обл.: "
							+ results.get(col).get(region) + " ");
				} else {
					map.put(ATTRIBUTE_REGION_TEXT, "Обл.: " + "Не указана ");
				}
				map.put(ATTRIBUTE_NAME_IMAGE, img);
				data.add(map);
			}
			String[] from = { ATTRIBUTE_BRAND_AND_MODEL_TEXT,
					ATTRIBUTE_STATE_TEXT, ATTRIBUTE_PRICE_TEXT,
					ATTRIBUTE_REGION_TEXT, ATTRIBUTE_NAME_IMAGE };
			int[] to = { R.id.item_results_text_brand_and_model,
					R.id.item_results_text_state, R.id.item_results_text_price,
					R.id.item_results_text_region, R.id.item_results_picture };
			SimpleAdapter sAdapter = new SimpleAdapter(this, data,
					R.layout.item, from, to);
			lvSimple = (ListView) findViewById(R.id.SimpleListView);
			lvSimple.setAdapter(sAdapter);
			final Intent more_info_activity = new Intent(this,
					MoreInfoActivity.class);

			lvSimple.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					int ID = 4;
					Log.d("Clicker", "itemClick: position = "
							+ position
							+ ", id = "
							+ ResultsSearchActivity.this.results.get(position)
									.get(ID));
					more_info_activity.putExtra("results",
							ResultsSearchActivity.this.results.get(position));
					startActivity(more_info_activity);

				}
			});

		} else {
			/*
			 * 
			 */
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case Constant.REQUEST_CODE_NOTIFOCATION: {
			Log.d("Activity", "Notification");
		}
		}
	}
}