package com.android.phone_market.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Николай
 * 
 */
public class CallInformationActivity extends Activity {

	private ArrayList<String> results;
	final String no_info = "Не указано";
	private TextView name_tv;
	private TextView phone_number_tv;
	private TextView other_phone_number_tv;
	private TextView e_mail_tv;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_information);
		Bundle extras = getIntent().getExtras();
		results = (ArrayList<String>) extras.get("results");
		name_tv = (TextView) findViewById(R.id.text_name);
		phone_number_tv = (TextView) findViewById(R.id.text_phone_number);
		other_phone_number_tv = (TextView) findViewById(R.id.text_other_phone_number);
		e_mail_tv = (TextView) findViewById(R.id.text_e_mail);
		int name = 6;
		int phone_number = 7;
		int other_phone_number = 8; 
		int e_mail = 9;
		if (results != null) {
			if (results.get(name).length() != 0) {
				name_tv.setText(results.get(name));
			} else {
				name_tv.setText(no_info);
			}

			if (results.get(phone_number).length() != 0) {
				phone_number_tv.setText(results.get(phone_number));
			} else {
				phone_number_tv.setText(no_info);
			}

			if (results.get(other_phone_number).length() != 0) {
				other_phone_number_tv.setText(results.get(other_phone_number));
			} else {
				other_phone_number_tv.setText(no_info);
			}

			if (results.get(e_mail).length() != 0) {
				e_mail_tv.setText(results.get(e_mail));
			} else {
				e_mail_tv.setText(no_info);
			}
		}
	}

}
