package com.android.phone_market.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * @author Николай
 * 
 */
public class DashboardLayoutActivity extends Activity implements
		OnClickListener {

	Button home_btn_buy_phone, home_btn_sell_phone, home_btn_preferences,
			home_btn_exit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboard_layout);
		home_btn_buy_phone = (Button) findViewById(R.id.home_btn_buy_phone);
		home_btn_sell_phone = (Button) findViewById(R.id.home_btn_sell_phone);
		home_btn_preferences = (Button) findViewById(R.id.home_btn_preferences);
		home_btn_exit = (Button) findViewById(R.id.home_btn_exit);
		home_btn_buy_phone.setOnClickListener(this);
		home_btn_sell_phone.setOnClickListener(this);
		home_btn_preferences.setOnClickListener(this);
		home_btn_exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.home_btn_buy_phone): // Button buy phone
			startActivity(new Intent(this, SearchPhoneActivity.class));
			break;
		case (R.id.home_btn_sell_phone): // Sell phone
			startActivity(new Intent(this, SellPhoneActivity.class));
			break;
		case (R.id.home_btn_preferences): // Preferences
			startActivity(new Intent(this, Preference.class));
			break;
		case (R.id.home_btn_exit): // Exit
			finish();
			break;
		}
	}
}