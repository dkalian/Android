package com.android.phone_market.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.SubMenu;
import com.android.phone_market.model.Constant;
import com.android.phone_market.model.NetworkAccess;
import com.android.phone_market.model.DataBaseOperations.SQLGenerator;
import com.android.phone_market.model.Dialogs.MyAlertDialog;
import com.android.phone_market.model.Service.BackgroungService;
import com.android.phone_market.model.Threads.ConnectionThread;
import com.android.phone_market.model.Threads.DataBaseOperation.ServiceWorkResultsReader;

/**
 * @author Николай
 * 
 */
public class SellPhoneActivity extends SherlockActivity implements TextWatcher,
		OnMenuItemClickListener {

	/** Called when the activity is first created. */

	AutoCompleteTextView mAutoCompleteRegion;
	private AutoCompleteTextView brand;
	EditText model, more_info, price, name, phone_number, other_phone_number,
			e_mail;
	Spinner color, state, region;
	private ProgressDialog pd;
	private Intent intent;
	private PendingIntent pi;
	private Executor executor;
	private String LogTag = "Sell Phone Activity: ";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_phone);

		brand = (AutoCompleteTextView) findViewById(R.id.sell_phone_brand);
		region = (Spinner) findViewById(R.id.sell_phone_region);
		model = (EditText) findViewById(R.id.sell_phone_model);
		color = (Spinner) findViewById(R.id.sell_phone_color);
		more_info = (EditText) findViewById(R.id.sell_phone_more_info);
		state = (Spinner) findViewById(R.id.sell_phone_state);
		price = (EditText) findViewById(R.id.sell_phone_price);
		name = (EditText) findViewById(R.id.sell_phone_name);
		phone_number = (EditText) findViewById(R.id.sell_phone_phone_number);
		other_phone_number = (EditText) findViewById(R.id.sell_phone_other_phone_number);
		e_mail = (EditText) findViewById(R.id.sell_phone_e_mail);

		intent = new Intent(SellPhoneActivity.this, ConnectionThread.class);
		pi = createPendingResult(1, intent, 0);

		/* Рисуем спиннер, указывающий цвет */

		ArrayAdapter<String> ColorSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, Constant.Color);
		ColorSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner ColorSpinner = (Spinner) findViewById(R.id.sell_phone_color);
		ColorSpinner.setAdapter(ColorSpinnerAdapter); // заголовок
		ColorSpinner.setSelection(0); // устанавливаем обработчик нажатия
		ColorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * показываем позицию нажатого элемента
				 * Toast.makeText(getBaseContext(), "Position = " + position,
				 * Toast.LENGTH_SHORT).show();
				 */
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				/*
				 * 
				 */
			}

		});
		/** Рисуем спиннер, указывающий цвет */

		/** Рисуем спиннер, указывающий регион */

		ArrayAdapter<String> RegionSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, Constant.Region);
		RegionSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner RegionColorSpinner = (Spinner) findViewById(R.id.sell_phone_region);
		RegionColorSpinner.setAdapter(RegionSpinnerAdapter); // заголовок
		RegionColorSpinner.setSelection(0); // устанавливаем обработчик нажатия
		RegionColorSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						/**
						 * показываем позицию нажатого элемента
						 * Toast.makeText(getBaseContext(), "Position = " +
						 * position, Toast.LENGTH_SHORT).show();
						 */
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						/*
						 * 
						 */
					}

				});
		/** Рисуем спиннер, указывающий регион */

		/** Рисуем спиннер, указывающий состояние */

		ArrayAdapter<String> StateSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, Constant.State);
		Spinner StateSpinner = (Spinner) findViewById(R.id.sell_phone_state);
		StateSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		StateSpinner.setAdapter(StateSpinnerAdapter);
		StateSpinner.setSelection(0);
		StateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * показываем позицию нажатого элемента
				 * Toast.makeText(getBaseContext(), "Position = " + position,
				 * Toast.LENGTH_SHORT).show();
				 */
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				/*
				 * 
				 */
			}

		});

		/** Рисуем спиннер, указывающий состояние */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * Кнопка поиска шерлок бара
		 */
		MenuItem info = menu.add(0, 5, Menu.NONE, "Отправить");
		// Картинка
		info.setIcon(R.drawable.ic_action_mail);
		info.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		// Обработчик нажатий
		info.setOnMenuItemClickListener(this);

		/*
		 * Выпадающее меню
		 */
		SubMenu subMenu1 = menu.addSubMenu(0, 100, Menu.NONE, "Главное меню");
		// Добаление одного элемента выпадающего меню (Настройки)
		subMenu1.add(0, 1, Menu.NONE, "Настройки")
		// иконка
				.setIcon(R.drawable.action_settings)
				// обработчик нажатия
				.setOnMenuItemClickListener(this);
		/*
		 * Остановка и выход
		 */
		subMenu1.add(0, 2, Menu.NONE, "Остановка автопоиска")
				.setIcon(R.drawable.device_access_flash_off)
				.setOnMenuItemClickListener(this);
		/*
		 * Выход
		 */
		subMenu1.add(0, 3, Menu.NONE, "Результаты автопоиска")
				.setIcon(R.drawable.navigation_cancel)
				.setOnMenuItemClickListener(this);

		MenuItem subMenu1Item = subMenu1.getItem();
		// Иконка выпадающего меню
		subMenu1Item.setIcon(R.drawable.ic_action_overflow);
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case 1: {
			startActivity(new Intent(this, Preference.class));
			break;
		}
		case 2: { 
			stopService(new Intent(this,BackgroungService.class));
			break;
		}
		case 3: {
			Log.i(LogTag , "Getting Results Search...");
			Log.i(LogTag, "Success! Start results:");
			System.out.println(ServiceWorkResultsReader.Read(this,
					Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS));
			Log.i(LogTag, "End results");
			if (ServiceWorkResultsReader.Read(this,
					Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS) != null) {
				if (ServiceWorkResultsReader.Read(this,
						Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS).size() != 0) {
					startActivity(new Intent(this, ResultsSearchActivity.class)
							.putExtra("results", ServiceWorkResultsReader.Read(
									this,
									Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS)));
				} else {
					MyAlertDialog.Show(this, "Информация", "Результатов нет.");
				}
			} else {
				MyAlertDialog.Show(this, "Информация", "Результатов нет.");
			}
			break;
		}
		case 5: {
			String str_brand = brand.getText().toString();
			String str_region = region.getSelectedItem().toString();
			String str_model = model.getText().toString();
			String str_color = color.getSelectedItem().toString();
			String str_more_info = more_info.getText().toString();
			String str_state = state.getSelectedItem().toString();
			String str_price = price.getText().toString();
			String str_name = name.getText().toString();
			String str_phone_number = phone_number.getText().toString();
			String str_other_phone_number = other_phone_number.getText()
					.toString();
			String str_e_mail = e_mail.getText().toString();

			/*
			 * Проверка на соединение с интернетом
			 */
			boolean connection = (new NetworkAccess(this).CheckConnection());
			if (connection) {
				boolean empty1;
				/*
				 * Старперская проверка на пустые ячейки в характеристике
				 */
				if ((str_brand.length() != 0) && (str_model.length() != 0)
						&& (str_price.length() != 0)) {
					empty1 = false;
				} else {
					empty1 = true;
					Toast.makeText(this,
							Constant.ERROR_MESSAGE_EMPTY_FIELD_STAR,
							Toast.LENGTH_SHORT).show();
				}
				boolean empty2;
				/*
				 * Старперская проверка на пустые ячейки в контактах
				 */
				if ((str_phone_number.length() == 0)
						&& (str_other_phone_number.length() == 0)
						&& (str_e_mail.length() == 0)) {
					empty2 = true;
					Toast.makeText(this,
							Constant.ERROR_MESSAGE_EMPTY_FIELD_CONTACT,
							Toast.LENGTH_SHORT).show();
					/*
					 * ячейки заполнены
					 */
				} else {
					empty2 = false;

				}

				/*
				 * Старперская проверка на пустые ячейки в контактах
				 */
				TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String EMEI = telephonyManager.getDeviceId();

				if (!empty1 && !empty2) {
					String SQL = SQLGenerator.Generate(str_brand, str_model,
							str_color, str_more_info, str_state, str_price,
							str_name, str_region, str_phone_number,
							str_other_phone_number, str_e_mail, EMEI);
					pd = ProgressDialog.show(this, "Обработка данных",
							"Пожалуйста пододжите...");
					if (executor == null) {
						executor = Executors.newFixedThreadPool(1);
						executor.execute(new ConnectionThread(this, pi, SQL));
					} else {
						executor.execute(new ConnectionThread(this, pi, SQL));
					}

				}
			} else {
				// Соединения нет
				Toast.makeText(this, Constant.INTERNET_NOT_ON,
						Toast.LENGTH_LONG).show();
			}
			break;
		}
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		pd.hide();
		Bundle extras = data.getExtras();
		String error = (String) extras.get("error");
		if (error.compareTo("true") != 0) {
			MyAlertDialog.Show(this, "Информация",
					"Ваше объявление успешно добавлено.");
		} else {
			MyAlertDialog.Show(this, "Ошибка", "Ошибка при добавлении записи.");
		}
	}
}
