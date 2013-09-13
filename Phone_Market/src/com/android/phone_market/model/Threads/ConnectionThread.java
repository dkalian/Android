package com.android.phone_market.model.Threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.phone_market.controller.SearchPhoneActivity;
import com.android.phone_market.model.Constant;
import com.android.phone_market.model.DataBaseOperations.ResultsInterpreter;

/**
 * @author Николай
 * 
 */
public class ConnectionThread implements Runnable {
	final String LogTag = "Service";
	private Context context;
	private PendingIntent pi;
	private String SQL;
	/*
	 * Флаг, указывающий, что ведется запись в базу данных. Без этой переменной
	 * неправильно будет выводиться сообщение о успешном добавлении в базу
	 * данных, так как при записи в БД statement не возвращает resultset
	 */
	private static boolean write = false;

	/**
	 * @param context
	 *            Контекст
	 * @param pi
	 *            Pending Intent
	 * @param SQL
	 *            SQL Запрос
	 */
	public ConnectionThread(Context context, PendingIntent pi, String SQL) {
		this.context = context;
		this.pi = pi;
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
		String error = new String();
		/*
		 * инициализация локальных переменных
		 */

		statement = ConnectToMySQL(Constant.DB_USERNAME, Constant.DB_PASSWORD,
				Constant.DB_URL, Constant.DB_PORT, Constant.DB_NAME);
		Intent intent = new Intent(context, SearchPhoneActivity.class);
		/*
		 * Проверка statement на null
		 */
		if (statement != null) {
			resultset = SQLRequestExecuor(SQL, statement, "utf8");
			error = "false";
		} else {
			error = "true";
		}
		if ((resultset != null)) {
			results = ResultsInterpreter.Interpretate(resultset);
			error = "false";
		} else {
			if (!write) {
				error = "true";
			}
		}
		intent.putExtra("error", error);
		intent.putExtra("results", results);

		try {
			pi.send(context, 0, intent);
		} catch (CanceledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			write = true;
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
