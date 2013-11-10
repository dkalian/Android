package com.ussd.quickussd.model.DataBaseOperations;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Nickolai
 * 
 */
public class DataBaseOperation {

	private static String LogTag = "DBoperations";

	/**
	 * @param context
	 *            Activity context
	 * @param bank
	 *            �������� �����, ��������� ��������
	 * @param keeper
	 *            ��� ��������� �����
	 * @param about_card ������� ���������� � �����
	 * @param account
	 *            4 ��������� ����� ������ �����
	 * @param actual_date
	 *            ���� ��������� �������� �����
	 * @return error_code int - ��� ������ <br>
	 *         0 - ������ ��� <br>
	 *         1 - ������ ��� ��������� � CV <br>
	 *         2 - ������ ��� ���������� � ��
	 */
	public static int AddCard(Context context, String bank, String keeper,
			String about_card, String account, String actual_date) {
		int error_code = 0; // ��� ������ (�� ��������� 0)
							// 0 - ������ ���
		ContentValues info = new ContentValues();

		try {
			Log.d(LogTag, "���������� � CV...");
			info.put(DataBaseConstant.TABLE_CARDS_INFO_FIELD_BANK_NAME, bank);
			info.put(DataBaseConstant.TABLE_CARDS_INFO_FIELD_KEEPER_NAME,
					keeper);
			info.put(DataBaseConstant.TABLE_CARDS_INFO_FIELD_ACCOUNT_NAME,
					account);
			info.put(DataBaseConstant.TABLE_CARDS_INFO_FIELD_ABOUT_CARD_NAME,
					about_card);
			info.put(DataBaseConstant.TABLE_CARDS_INFO_FIELD_ACTUAL_DATE_NAME,
					actual_date);
			Log.d(LogTag, "���������");
		} catch (Exception e) {
			Log.e(LogTag, "������!");
			e.printStackTrace();
			error_code = 1; // 1 - ������ ��� ��������� ���������� � content
							// values
			return error_code;
		}

		DAO dao = new DAO(context);

		SQLiteDatabase db = dao.getWritableDatabase();

		try {
			Log.d(LogTag, "��������� � �� ������...");
			db.insertOrThrow(DataBaseConstant.TABLE_CARDS_INFO_NAME, null, info);
			Log.d(LogTag, "���������");
		} catch (Exception e) {
			Log.e(LogTag, "������!");
			e.printStackTrace();
			error_code = 2;
			return error_code;
		}
		db.close();
		return error_code;
	}

	/**
	 * @param context
	 *            Activivty context
	 * @return ArrayList<HashMap<String, Object>>
	 */
	public static ArrayList<HashMap<String, Object>> GetCards(Context context) {
		ArrayList<HashMap<String, Object>> cards = new ArrayList<HashMap<String, Object>>();
		DAO dao = new DAO(context);
		SQLiteDatabase db = dao.getReadableDatabase();
		Cursor c = db.query(DataBaseConstant.TABLE_CARDS_INFO_NAME, null, null,
				null, null, null, null);
		if (c.moveToFirst()) {
			do {

				int idf = c.getColumnIndex("id");

				int fKeeper = c
						.getColumnIndex(DataBaseConstant.TABLE_CARDS_INFO_FIELD_KEEPER_NAME);
				int fAccount = c
						.getColumnIndex(DataBaseConstant.TABLE_CARDS_INFO_FIELD_ACCOUNT_NAME);
				int fActualDate = c
						.getColumnIndex(DataBaseConstant.TABLE_CARDS_INFO_FIELD_ACTUAL_DATE_NAME);
				int fBank = c
						.getColumnIndex(DataBaseConstant.TABLE_CARDS_INFO_FIELD_BANK_NAME);
				int fAbout = c
						.getColumnIndex(DataBaseConstant.TABLE_CARDS_INFO_FIELD_ABOUT_CARD_NAME);

				String id = c.getString(idf);
				String Keeper = c.getString(fKeeper);
				String Account = c.getString(fAccount);
				String ActualDate = c.getString(fActualDate);
				String Bank = c.getString(fBank);
				String About = c.getString(fAbout);

				HashMap<String, Object> temp = new HashMap<String, Object>();

				temp.put(DataBaseConstant.HASH_MAP_CARDS_INFO_KEEPER_KEY,
						Keeper);
				temp.put(
						DataBaseConstant.HASH_MAP_CARDS_INFO_ACCOUNT_NUMBER_KEY,
						Account);
				temp.put(DataBaseConstant.HASH_MAP_CARDS_INFO_ACTUAL_DATE_KEY,
						ActualDate);
				temp.put(DataBaseConstant.HASH_MAP_CARDS_INFO_BANK_KEY, Bank);
				temp.put(DataBaseConstant.HASH_MAP_CARDS_ABOUT_CARD_KEY, About);
				temp.put(DataBaseConstant.HASH_MAP_CARDS_INFO_ID_KEY, id);

				cards.add(temp);
				Log.d("DataBaseOperations", "Card info:");
				Log.d("DataBaseOperations", "id: " + id);
				Log.d("DataBaseOperations", "Keeper: " + Keeper);
				Log.d("DataBaseOperations", "Account: " + Account);
				Log.d("DataBaseOperations", "Actual date: " + ActualDate);
				Log.d("DataBaseOperations", "Bank: " + Bank);
				Log.d("DataBaseOperations", "____________________");

			} while (c.moveToNext());
		} else {
			// No data
		}
		db.close();
		return cards;
	}

	/**
	 * @param context
	 *            activity context
	 * @param id
	 *            id remove card
	 * @return if remode success - true <br>
	 *         else - false
	 */
	public static boolean RemoveCard(Context context, String id) {
		boolean isSuccess = false;
		DAO dao = new DAO(context);
		SQLiteDatabase db = dao.getWritableDatabase();
		try {
			db.delete(DataBaseConstant.TABLE_CARDS_INFO_NAME, "id = " + id,
					null);
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			Log.d("Remove Card", "error!!!");
		}
		db.close();
		return isSuccess;
	}
}
