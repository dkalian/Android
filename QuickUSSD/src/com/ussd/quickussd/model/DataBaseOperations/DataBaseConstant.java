package com.ussd.quickussd.model.DataBaseOperations;

/**
 * @author Nickolai
 * 
 */
public class DataBaseConstant {
	/**
	 * Название базы данных
	 */
	public static final String DBNAME = "QUSSDDB";
	/**
	 * Название таблицы с информацией о счетах
	 */
	public static final String TABLE_CARDS_INFO_NAME = "cards";
	/*
	 * Поля таблицы с информацией о счетах
	 */
	/**
	 * Банк, выдавший карту
	 */
	public static final String TABLE_CARDS_INFO_FIELD_BANK_NAME = "bank";

	/**
	 * Держатель (владелец карты)
	 */
	public static final String TABLE_CARDS_INFO_FIELD_KEEPER_NAME = "keeper";
	/**
	 * 4 последние цифры от номера карты <br>
	 * **** **** **** ХХХХ
	 */
	public static final String TABLE_CARDS_INFO_FIELD_ACCOUNT_NAME = "account";
	/**
	 * Дата окончания действия карты
	 */
	public static final String TABLE_CARDS_INFO_FIELD_ACTUAL_DATE_NAME = "actual_date";

	/**
	 * Краткое описание карты
	 */
	public static final String TABLE_CARDS_INFO_FIELD_ABOUT_CARD_NAME = "about_card";

	
	/*
	 * Поля таблицы с информацией о счетах
	 */
	/*
	 * HASH MAP CARD INFO KEYS
	 */
	/**
	 * Actual date key
	 */
	public static final String HASH_MAP_CARDS_INFO_ACTUAL_DATE_KEY = "actual_date";

	/**
	 * Keeper key
	 */
	public static final String HASH_MAP_CARDS_INFO_KEEPER_KEY = "keeper";

	/**
	 * Account number
	 */
	public static final String HASH_MAP_CARDS_INFO_ACCOUNT_NUMBER_KEY = "acccount";

	/**
	 * Bank key
	 */

	public static final String HASH_MAP_CARDS_INFO_BANK_KEY = "bank";

	/**
	 * ID key
	 */
	public static final String HASH_MAP_CARDS_INFO_ID_KEY = "id";
	
	/**
	 * About key
	 */
	public static final String HASH_MAP_CARDS_ABOUT_CARD_KEY = "about_card";
	
	/*
	 * HASH MAP CARD INFO KEYS
	 */

}
