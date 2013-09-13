package com.android.phone_market.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Николай
 * 
 */
public class AboutPhonePropitiesActivity extends Activity {

	private ArrayList<String> results;
	private TextView more_info_tv;
	private TextView price_tv;
	private TextView state_tv;
	private TextView color_tv;
	private TextView brand_and_movel_tv;
	private TextView region_tv;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_phone_propities);
		Bundle extras = getIntent().getExtras();
		results = (ArrayList<String>) extras.get("results");

		brand_and_movel_tv = (TextView) findViewById(R.id.text_brand_app);
		color_tv = (TextView) findViewById(R.id.text_color_app);
		state_tv = (TextView) findViewById(R.id.text_state_app);
		price_tv = (TextView) findViewById(R.id.text_price_app);
		// 
		more_info_tv = (TextView) findViewById(R.id.text_more_info_app);
		region_tv = (TextView) findViewById(R.id.text_region_app);
		// 

		if (results != null) {
			int brand_and_model = 0;
			int state = 1;
			int price = 2;
			int region = 3;
			int color = 10;
			int more_info = 5;

			final String no_info = "Не указано";

			brand_and_movel_tv.setText(results.get(brand_and_model));
			if (results.get(color).length() != 0) {
				color_tv.setText(results.get(color));
			} else {
				color_tv.setText(no_info);
			}
			if (results.get(state).length() != 0) {
				state_tv.setText(results.get(state));
			} else {
				state_tv.setText(no_info);
			}
			if (results.get(price).length() != 0) {
				price_tv.setText(results.get(price) + " $");
			} else {
				price_tv.setText(no_info);
			}
			if (results.get(more_info).length() != 0) {
				more_info_tv.setText(results.get(more_info));
			} else {
				more_info_tv.setText(no_info);
			}

			if (results.get(region).length() != 0) {
				region_tv.setText(results.get(region));
			} else {
				region_tv.setText(no_info);
			}

		}
	}

}
