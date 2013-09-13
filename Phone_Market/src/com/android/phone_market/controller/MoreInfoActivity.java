package com.android.phone_market.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Николай
 */
public class MoreInfoActivity extends Activity implements OnClickListener {
	ArrayList<String> results = new ArrayList<String>();

	TextView brand_and_movel_tv, color_tv, state_tv, price_tv;

	private TextView region_tv;

	private Button more_info_button, call_button;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_info);

		brand_and_movel_tv = (TextView) findViewById(R.id.text_brand);
		color_tv = (TextView) findViewById(R.id.text_color);
		state_tv = (TextView) findViewById(R.id.text_state);
		price_tv = (TextView) findViewById(R.id.text_price);
		region_tv = (TextView) findViewById(R.id.text_region);
		more_info_button = (Button) findViewById(R.id.more_info_button);
		call_button = (Button) findViewById(R.id.call_button);

		call_button.setOnClickListener(this);
		more_info_button.setOnClickListener(this);

		final String no_info = "Не указано";

		Bundle extras = getIntent().getExtras();
		results = (ArrayList<String>) extras.get("results");
		if (results != null) {
			int brand_and_model = 0;
			int state = 1;
			int price = 2;
			int region = 3;
			int color = 10;
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

			if (results.get(region).length() != 0) {
				region_tv.setText(results.get(region));
			} else {
				region_tv.setText(no_info);
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more_info_button: {
			startActivity(new Intent(this, AboutPhonePropitiesActivity.class)
					.putExtra("results", results));
			break;
		}
		case R.id.call_button: {
			startActivity(new Intent(this, CallInformationActivity.class)
					.putExtra("results", results));
			break;
		}
		}

	}
}
