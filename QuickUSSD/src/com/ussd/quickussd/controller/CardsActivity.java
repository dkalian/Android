package com.ussd.quickussd.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ussd.quickussd.R;
import com.ussd.quickussd.model.Constant;
import com.ussd.quickussd.model.USSDCodePreparer;
import com.ussd.quickussd.model.CustomAlertDialogs.MyAlertDialog;
import com.ussd.quickussd.model.DataBaseOperations.DataBaseOperation;
import com.ussd.quickussd.model.adapter.CardsAdapter;

/**
 * @author Nickolai
 * @version 0.0.1
 */
public class CardsActivity extends ActionBarActivity {

	Button addCard;
	int DIALOG_ADD_CARD = 1;
	Dialog dialog;

	private ListView cardsLv;
	String code = "";
	private CardsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cards);

		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.ActionBar)));

		adapter = (new CardsAdapter(
				DataBaseOperation.GetCards(CardsActivity.this),
				CardsActivity.this));

		cardsLv = (ListView) findViewById(R.id.activity_cards_cards_list);
		cardsLv.setAdapter(adapter);

		cardsLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("Clicker", "itemClick: position = " + id);
				String bank = ((TextView) view
						.findViewById(R.id.item_card_bank_text)).getText()
						.toString();
				String account = ((TextView) view
						.findViewById(R.id.item_card_account_number_text))
						.getText().toString();
				String ussd = USSDCodePreparer.prepeare(bank, account);
				if (ussd.length() != 0) {
					call(ussd);
				} else {
					Log.d("Cards Activity", "Incorrect USSD!");
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cards, menu);

		MenuItem add = menu.findItem(R.id.add_card);
		add.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				dialog = onCreateDialog(DIALOG_ADD_CARD);
				dialog.show();
				return false;
			}
		});
		return true;
	}

	View view;
	Button save;
	Button cancel;

	EditText keeper;
	EditText account;
	EditText actual_date;
	EditText about_card;

	/** (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@SuppressWarnings("deprecation")
	public Dialog onCreateDialog(int id) {
		if (id == DIALOG_ADD_CARD) {
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setTitle("Добавление карточки");
			// создаем view из dialog.xml
			view = (RelativeLayout) getLayoutInflater().inflate(
					R.layout.add_card_dialog, null);

			/* Рисуем спиннер, указывающий банк */

			ArrayAdapter<String> BankSpinnerAdapter = new ArrayAdapter<String>(
					CardsActivity.this, android.R.layout.simple_spinner_item,
					Constant.BANKS);
			BankSpinnerAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			final Spinner BankSpinner = (Spinner) view
					.findViewById(R.id.dialog_add_card_spinner_bank);
			BankSpinner.setAdapter(BankSpinnerAdapter);
			BankSpinner.setSelection(0); // Элемент по умолчанию
			// устанавливаем обработчик нажатия
			BankSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					/*
					 * показываем позицию нажатого элемента
					 */
					// Toast.makeText(getBaseContext(), "Position = " +
					// position,
					// Toast.LENGTH_SHORT).show();

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}

			});
			/* Рисуем спиннер, указывающий банк */

			save = (Button) view.findViewById(R.id.dialog_add_card_button_save);
			cancel = (Button) view
					.findViewById(R.id.dialog_add_card_button_cancel);
			keeper = (EditText) view
					.findViewById(R.id.dialog_add_card_keeper_text);
			account = (EditText) view
					.findViewById(R.id.dialog_add_card_account_number);
			actual_date = (EditText) view
					.findViewById(R.id.dialog_add_card_actual_date_text);
			about_card = (EditText) view
					.findViewById(R.id.dialog_add_card_about_card_text);

			// Действие, при нажатии на кнопку "Сохранить"

			save.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (checkInputData(keeper, account)) {
						switch (DataBaseOperation.AddCard(CardsActivity.this,
								BankSpinner.getSelectedItem().toString(),
								keeper.getText().toString(), about_card
										.getText().toString(), account
										.getText().toString(), actual_date
										.getText().toString())) {
						case 0: {
							Toast.makeText(CardsActivity.this,
									"Карта успешно добавлена",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
							adapter = (new CardsAdapter(DataBaseOperation
									.GetCards(CardsActivity.this),
									CardsActivity.this));
							cardsLv.setAdapter(adapter);
							break;
						}
						case 1: {
							MyAlertDialog.Show(CardsActivity.this, "Ошибка!",
									Constant.ERROR_INSERT_TO_CV);
							break;
						}
						case 2: {
							MyAlertDialog.Show(CardsActivity.this, "Ошибка!",
									Constant.ERROR_INSERT);
							break;
						}
						}
					}

				}
			});

			// Cancel click
			cancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			// устанавливаем ее, как содержимое тела диалога
			adb.setView(view);
			return adb.create();
		}

		return super.onCreateDialog(id);
	}

	private String ERROR_KEEPER_EMPTY = "укажите владелеца";
	private String ERROR_ACCOUNT_EMPTY = "укажите 4 последние цифры карты";
	private String ERROR_ACCOUNT_INCORRECT_NUMBER = "Нужно указать 4 последние цифры номера карты";

	
	private boolean checkInputData(EditText keeper, EditText account) {

		if (keeper.length() == 0) {
			keeper.setError(ERROR_KEEPER_EMPTY);
			return false;
		}
		if (account.length() == 0) {
			account.setError(ERROR_ACCOUNT_EMPTY);
			return false;
		}
		if (account.length() != 4) {
			account.setError(ERROR_ACCOUNT_INCORRECT_NUMBER);
			return false;
		}
		return true;
	}

	protected void call(String phoneNumber) {
		try {
			Log.d("Cards Activity", "Call USSD:" + phoneNumber + "#");
			phoneNumber += Uri.encode("#");
			// создаём новое Activity и делаем вызов
			startActivityForResult(
					new Intent("android.intent.action.CALL", Uri.parse("tel:"
							+ phoneNumber)), 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show();
		}
	}
}
