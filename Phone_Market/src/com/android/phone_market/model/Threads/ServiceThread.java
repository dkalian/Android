package com.android.phone_market.model.Threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.android.phone_market.model.Constant;
import com.android.phone_market.model.CustomNotification;
import com.android.phone_market.model.DataBaseOperations.ResultsInterpreter;
import com.android.phone_market.model.Threads.DataBaseOperation.ServiceWorkResultsWriter;

/**
 * @author Николай
 * 
 */
public class ServiceThread implements Runnable {

	private NotificationManager nm;

	private String LogTag = "ServiceThread: ";
	private String SQL;
	private Context context;

	/**
	 * @param context Context
	 * @param SQL String
	 * @param nm Notification Manager
	 */
	public ServiceThread(Context context, String SQL,
			NotificationManager nm) {
		this.nm = nm;
		this.context = context;
		this.SQL = SQL;
	}

	@Override
	public void run() {

		/*
		 * инициализация локальных переменных
		 */
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		Statement statement = null;
		ResultSet resultset = null;
		/*
		 * инициализация локальных переменных
		 */
		statement = ConnectToMySQL(Constant.DB_USERNAME, Constant.DB_PASSWORD,
				Constant.DB_URL, Constant.DB_PORT, Constant.DB_NAME);
		/*
		 * Проверка statement на null
		 */

		if (statement != null)
			resultset = SQLRequestExecuor(SQL, statement, "utf8");

		if (resultset != null) {
			results = ResultsInterpreter.Interpretate(resultset);
			if (results.size() != 0) {
				Log.i(LogTag, "Results to be writed in local DB");
				ServiceWorkResultsWriter.WriteResultsSearch(context,
						Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS, results);
				new CustomNotification().ShowNotification(context,
						"Phone Market: Новое событие", "Результаты поиска:",
						"Найден телефон, соответсвующий критериям поиска", nm);
			} else {
				Log.w(LogTag,
						"Results size = 0! Results not be writed to local DB");
			}

		}
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param Username
	 *            Имя пользователя
	 * @param Password
	 *            Пароль
	 * @param URL
	 *            Адрес базы данных
	 * @param Port
	 *            Порт
	 * @param DBName
	 *            Название базы данных
	 * @return Statement состояние
	 */
	private static Statement ConnectToMySQL(String Username, String Password,
			String URL, String Port, String DBName) {
		Connection conn = null;
		final String LogTag = "ConnectToMySQL";
		Statement statement = null;

		/*
		 * Параметры подключения к БД
		 */
		Properties properties = new Properties();
		properties.setProperty("user", Username);
		properties.setProperty("password", Password);
		properties.setProperty("useUnicode", "true");
		properties.setProperty("characterEncoding", "utf8");
		properties.setProperty("connectTimeout", "15000");

		/*
		 * Параметры подключения к БД
		 */

		try {
			Log.d(LogTag, "Подключение драйвера jdbc...");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Log.d(LogTag, "Драйвер подключен");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.d(LogTag, "Ошибка подключения драйвера! Драйвер не найден!");
			e.printStackTrace();
		}

		try {
			Log.d(LogTag, "Создание подключения...");

			conn = DriverManager.getConnection("jdbc:mysql://" + URL + ":"
					+ Port + "/" + DBName + "", properties);

			Log.d(LogTag, "Подключение создано");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		/*
		 * Проверка на соданное соединение
		 */
		if (conn != null) {
			try {
				Log.d(LogTag, "Создание состояния...");
				statement = (Statement) conn.createStatement();
				Log.d(LogTag, "Состояние создано");
			} catch (SQLException e) {
				Log.d(LogTag, "Ошибка при создании состояния!");
				e.printStackTrace();
			}
		}
		return statement;
	}

	private static ResultSet SQLRequestExecuor(String SQLRequest,
			Statement statement, String encoding) {

		ResultSet resultset = null;
		final String LogTag = "ConnectToMySQL";
		Log.d(LogTag, "Настройка кодировки...");

		try {
			statement.executeUpdate("set character_set_client='" + encoding
					+ "'");
		} catch (SQLException e) {
			Log.d(LogTag, "Ошибка при настройке кодировки!");
			e.printStackTrace();
		}

		try {
			statement.executeUpdate("set character_set_results='" + encoding
					+ "'");
		} catch (SQLException e) {
			Log.d(LogTag, "Ошибка при настройке кодировки!");
			e.printStackTrace();
		}

		try {
			statement.executeUpdate("set collation_connection='" + encoding
					+ "_general_ci'");
			Log.d(LogTag, "Кодировка настроена");
		} catch (SQLException e) {
			Log.d(LogTag, "Ошибка при настройке кодировки!");
			e.printStackTrace();
		}

		/*
		 * Исправить !!!
		 */

		try {
			Log.d(LogTag, "Отправка SQL на чтение ...");
			statement.executeQuery(SQLRequest);
			Log.d(LogTag, "Запрос на чтение отправлен");
		} catch (SQLException e) {
			/*
			 * 
			 */
		}

		try {
			Log.d(LogTag, "Отправка SQL запроса на запись...");
			statement.executeUpdate(SQLRequest);
			Log.d(LogTag, "Запрос на запись отправлен");
		} catch (SQLException e) {
			/*
			 * 
			 */
		}

		/*
		 * Исправить !!!
		 */

		try {
			Log.d(LogTag, "Получение результата...");
			resultset = statement.getResultSet();
		} catch (SQLException e) {
			Log.d(LogTag, "Ошибка при получении результата!");
			e.printStackTrace();
		}

		if (resultset != null) {
			Log.d(LogTag, "Результат получен");

		}

		return resultset;
	}

}
