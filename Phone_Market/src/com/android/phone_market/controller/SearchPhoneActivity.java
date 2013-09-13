package com.android.phone_market.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.SubMenu;
import com.android.phone_market.model.Constant;
import com.android.phone_market.model.NetworkAccess;
import com.android.phone_market.model.DataBaseOperations.DataBaseWorker;
import com.android.phone_market.model.DataBaseOperations.SQLGenerator;
import com.android.phone_market.model.Dialogs.MyAlertDialog;
import com.android.phone_market.model.Service.BackgroungService;
import com.android.phone_market.model.Threads.ConnectionThread;
import com.android.phone_market.model.Threads.DataBaseOperation.ServiceWorkResultsReader;

/**
 * @author Николай
 * 
 */

public class SearchPhoneActivity extends SherlockActivity implements
		TextWatcher, OnMenuItemClickListener {

	AutoCompleteTextView mAutoCompleteRegion;

	/**
	 * Метка логгера
	 */

	public static final String TAG = "" + SearchPhoneActivity.class.getName();

	ResultSet result;

	EditText model, price;
	Spinner color, state, region;
	AutoCompleteTextView brand;
	Button search;
	ArrayList<ArrayList<String>> results;
	CheckBox colorCheck, stateCheck, regionCheck;

	final String ATTRIBUTE_BRAND_AND_MODEL_TEXT = "brand_and_model";
	final String ATTRIBUTE_STATE_TEXT = "state";
	final String ATTRIBUTE_PRICE_TEXT = "price";
	final String ATTRIBUTE_REGION_TEXT = "region";
	final String ATTRIBUTE_NAME_IMAGE = "image";
	ListView lvSimple;

	SearchPhoneActivity searchPhoneActivity;
	String str_brand;
	String str_region;
	String str_model;
	String str_color;
	String str_state;
	String str_price;

	/**
	 * ProgressDialog
	 */

	public ProgressDialog pd;

	private ResultSet rs;

	private Executor executor;

	private PendingIntent pi;

	private Intent intent;

	private String LogTag = SearchPhoneActivity.class.getName() + " : ";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_phone);

		brand = (AutoCompleteTextView) findViewById(R.id.search_phone_brand);
		region = (Spinner) findViewById(R.id.search_phone_region);
		model = (EditText) findViewById(R.id.search_phone_model);
		color = (Spinner) findViewById(R.id.search_phone_color);
		state = (Spinner) findViewById(R.id.search_phone_state);
		price = (EditText) findViewById(R.id.search_price);
		colorCheck = (CheckBox) findViewById(R.id.color_check_box);
		stateCheck = (CheckBox) findViewById(R.id.state_check_box);
		regionCheck = (CheckBox) findViewById(R.id.region_check_box);

		intent = new Intent(SearchPhoneActivity.this, ConnectionThread.class);
		pi = createPendingResult(0, intent, 0);

		/* Рисуем спиннер, указывающий цвет */

		ArrayAdapter<String> ColorSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, Constant.Color);
		ColorSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final Spinner ColorSpinner = (Spinner) findViewById(R.id.search_phone_color);
		ColorSpinner.setAdapter(ColorSpinnerAdapter); // заголовок
		ColorSpinner.setSelection(0); // устанавливаем обработчик нажатия
		ColorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * 
				 */
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				/*
				 * 
				 */
			}

		});

		/* Рисуем спиннер, указывающий цвет */

		/* Рисуем спиннер, указывающий регион */

		ArrayAdapter<String> RegionSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, Constant.Region);
		RegionSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final Spinner RegionSpinner = (Spinner) findViewById(R.id.search_phone_region);
		RegionSpinner.setAdapter(RegionSpinnerAdapter); // заголовок
		RegionSpinner.setSelection(0); // устанавливаем обработчик нажатия
		RegionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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
		/* Рисуем спиннер, указывающий регион */

		/* Рисуем спиннер, указывающий состояние */

		ArrayAdapter<String> StateSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, Constant.State);
		final Spinner StateSpinner = (Spinner) findViewById(R.id.search_phone_state);
		StateSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		StateSpinner.setAdapter(StateSpinnerAdapter); // заголовок
		StateSpinner.setSelection(0); // устанавливаем обработчик нажатия
		StateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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

		/* Рисуем спиннер, указывающий состояние */

		/*
		 * Проверка на сохранненые параметры поиска
		 */
		final ContentValues savedrecord = new DataBaseWorker(this)
				.CheckForSavedRecords(Constant.LOCAL_DB_NAME,
						Constant.TABLE_NAME_SAVE_PARAMETERS);

		OnClickListener OnPositiveClisckListner = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				brand.setText((String) savedrecord.get("brand"));
				model.setText((String) savedrecord.get("model"));
				price.setText((String) savedrecord.get("price"));
				ColorSpinner.setSelection((Integer) savedrecord
						.get("checkedColor"));
				StateSpinner.setSelection((Integer) savedrecord
						.get("checkedState"));
				RegionSpinner.setSelection((Integer) savedrecord
						.get("checkedRegion"));
				colorCheck.setChecked(getBoolean((Integer) savedrecord
						.get("colorCheck")));
				stateCheck.setChecked(getBoolean((Integer) savedrecord
						.get("stateCheck")));
				regionCheck.setChecked(getBoolean((Integer) savedrecord
						.get("regionCheck")));
				color.setEnabled(getBoolean((Integer) savedrecord
						.get("colorCheck")));
				state.setEnabled(getBoolean((Integer) savedrecord
						.get("stateCheck")));
				region.setEnabled(getBoolean((Integer) savedrecord
						.get("regionCheck")));
			}
		};

		OnClickListener OnNegativeClisckListner = new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				new DataBaseWorker(SearchPhoneActivity.this)
						.DeleteSavedRecords();
			}
		};

		if ((savedrecord != null) && (savedrecord.size() != 0)) {
			MyAlertDialog.Show(this, "Сообщение",
					"Найдена ранее заполненная форма поиска, восстановить?",
					"Да", "Нет", OnPositiveClisckListner,
					OnNegativeClisckListner);
		}
		/*
		 * Проверка на сохранненые параметры поиска
		 */

	}

	/**
	 * @param input
	 *            1/0
	 * @return boolean
	 */
	public static boolean getBoolean(int input) {
		boolean output = true;
		if (input == 1) {
			output = true;
		} else {
			if (input == 0) {
				output = false;
			}
		}
		return output;
	}

	/**
	 * @param v
	 *            View
	 */
	public void onCheckboxClicked(View v) {
		boolean checked = ((CheckBox) v).isChecked();
		switch (v.getId()) {
		case R.id.color_check_box: {
			if (checked) {
				color.setEnabled(true);
			} else
				color.setEnabled(false);
			break;
		}
		case R.id.state_check_box: {
			if (checked) {
				state.setEnabled(true);
			} else {
				state.setEnabled(false);
			}
			break;
		}
		case R.id.region_check_box: {
			if (checked) {
				region.setEnabled(true);
			} else {
				region.setEnabled(false);
			}
			break;
		}
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		/*
		 * 
		 */
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		/*
		 *
		 */
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		/*
		 * 
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * Кнопка поиска шерлок бара
		 */
		MenuItem info = menu.add(0, 5, Menu.NONE, "Поиск");
		// Картинка
		info.setIcon(R.drawable.action_search);
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

	/**
	 * Getter
	 * 
	 * @return переменная типа Result Set
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * Setter
	 * 
	 * @param rs
	 *            переменная типа Result Set
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {

		case 3: {
			Log.i(LogTag, "Getting Results Search...");
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

		case 1: {
			startActivity(new Intent(this, Preference.class));
			break;
		}

		case 2: {
			stopService(new Intent(this, BackgroungService.class));
			break;
		}

		case 5: {
			boolean empty = true;
			/*
			 * Получение значений из полей ввода
			 */
			String str_brand = brand.getText().toString();
			String str_region = region.getSelectedItem().toString();
			String str_model = model.getText().toString();
			String str_color = color.getSelectedItem().toString();
			String str_state = state.getSelectedItem().toString();
			String str_price = price.getText().toString();
			int checked_color = color.getSelectedItemPosition();
			int checked_state = state.getSelectedItemPosition();
			int checked_region = region.getSelectedItemPosition();
			if ((brand.length() == 0) || (model.length() == 0)
					|| (price.length() == 0)) {
				empty = true;
			} else {
				empty = false;
			}

			if (new NetworkAccess(this).CheckConnection()) {
				if (!empty) {
					/*
					 * Запрос в базу данных
					 */
					String SQL = SQLGenerator.Generate(str_brand, str_model,
							str_color, str_state, str_region, str_price,
							colorCheck.isChecked(), stateCheck.isChecked(),
							regionCheck.isChecked());

					/*
					 * Сохранить параметры поиска в локальную базу
					 */

					new DataBaseWorker(this).SaveSearchParameters(str_brand,
							str_model, checked_color, checked_state,
							checked_region, str_price, colorCheck.isChecked(),
							stateCheck.isChecked(), regionCheck.isChecked());

					/*
					 * Сохранить параметры поиска в локальную базу
					 */

					/*
					 * Сохранение сформированного запроса для сервиса
					 */

					new DataBaseWorker(this).SaveSQLRequest(SQL);
					/*
					 * Сохранение сформированного запроса для сервиса
					 */

					pd = ProgressDialog.show(this, "Обработка данных",
							"Пожалуйста пододжите...");
					if (executor == null) {
						executor = Executors.newFixedThreadPool(1);
						executor.execute(new ConnectionThread(this, pi, SQL));
					} else {
						executor.execute(new ConnectionThread(this, pi, SQL));
					}

				} else {
					Toast.makeText(this,
							Constant.ERROR_MESSAGE_EMPTY_FIELD_STAR,
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, Constant.INTERNET_NOT_ON,
						Toast.LENGTH_LONG).show();
			}
			break;

		}
		}
		return false;
	}

	/**
	 * Точка возврата для потока
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		OnClickListener OnPositiveClickListner = new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				startService(new Intent(SearchPhoneActivity.this,
						BackgroungService.class));
			}
		};

		pd.hide();
		Bundle extras = data.getExtras();
		results = (ArrayList<ArrayList<String>>) extras.get("results");
		String error = (String) extras.get("error");
		System.out.println(results);
		if (error.compareTo("true") != 0) {
			if (results.size() == 0) {
				// Вопрос "хотите ли вы автоматически искать данный телефон?"

				MyAlertDialog
						.Show(this,
								"Поиск",
								"По вашему запросу ничего не найдено, искать автоматически?",
								"Да", "Нет", OnPositiveClickListner, null);

			} else {
				Intent results_activity = new Intent(this,
						ResultsSearchActivity.class);
				results_activity.putExtra("results", results);
				startActivity(results_activity);
			}
		} else {
			MyAlertDialog
					.Show(this, "Ошибка!",
							"Результат не получен, возможно сервер не доступен. Повторите попытку позже.");
		}

	}

}