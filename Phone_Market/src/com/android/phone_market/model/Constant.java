package com.android.phone_market.model;

/**
 * @author Николай
 * 
 */
public class Constant {

	/**
	 * Названия областей Республики Беларусь
	 */

	public static final String[] Region = { "Гомельская", "Гродненская",
			"Витебская", "Минская", "Брестская", "Могилевская" };

	/**
	 * Цвета телефонов
	 */

	public static final String[] Color = { "Черный", "Белый", "Красный",
			"Серый", "Желтый" };
	/**
	 * Состояния телефонов
	 */

	public static final String[] State = { "Плохое", "Среднее", "Хорошее",
			"Идеальное" };
	/**
	 * Сообщение об ошибке, поля со * не заполнены
	 */

	public static final String ERROR_MESSAGE_EMPTY_FIELD_STAR = "Одно из полей со * пустое";

	/**
	 * Сообщение об ошибке, контактные данные не заполнены
	 */

	public static final String ERROR_MESSAGE_EMPTY_FIELD_CONTACT = "Укажите способ связаться с вами";

	/**
	 * Поле "брэнд" в таблице
	 */

	public static final String DB_FILED_BRAND = "brand";

	/**
	 * Поле "модель" в таблице
	 */

	public static final String DB_FILED_MODEL = "model";

	/**
	 * Поле "цвет" в таблице
	 */

	public static final String DB_FILED_COLOR = "color";

	/**
	 * Поле "больше информации" в таблице
	 */

	public static final String DB_FILED_MORE_INFO = "more_info";

	/**
	 * Поле "состояние" в таблице
	 */

	public static final String DB_FILED_STATE = "state";

	/**
	 * Поле "цена" в таблице
	 */

	public static final String DB_FILED_PRICE = "price";

	/**
	 * Поле "картинка" в таблице
	 */

	public static final String DB_FILED_IMAGE = "image";

	/**
	 * Поле "Имя владельца" в таблице
	 */

	public static final String DB_FILED_NAME = "name";

	/**
	 * Поле "регион" в таблице
	 */

	public static final String DB_FILED_REGION = "region";

	/**
	 * Поле "номер телефона" в таблице
	 */

	public static final String DB_FILED_PHONE_UMBER = "phone_number";

	/**
	 * Поле "другой номер телефона" в таблице
	 */

	public static final String DB_FILED_OTHER_PHONE_UMBER = "other_phone_number";

	/**
	 * Поле "электронная почта" в таблице
	 */

	public static final String DB_FILED_E_MAIL = "e_mail";

	/**
	 * Имя базы данных
	 */

	public static final String DB_NAME = "android";

	/**
	 * Имя базы данных
	 */

	public static final String LOCAL_DB_NAME = "PhoneM";

	/**
	 * Имя пользователя
	 */

	public static final String DB_USERNAME = "android";

	/**
	 * Пароль
	 */

	public static final String DB_PASSWORD = "androiduser";

	/**
	 * Адрес сервера
	 */

	public static final String DB_URL = "192.168.135.1";

	/**
	 * Порт
	 */

	public static final String DB_PORT = "3306";

	/**
	 * Метка для логера
	 */

	public static final String Logger_TAG = "My Logger";

	/**
	 * Нет соединения с интернетом
	 */

	public static final String INTERNET_NOT_ON = "Phone Market: Нет соединения с интернетом. Подключитесь и попытайтесь снова.";

	/**
	 * Имя таблицы для хранения копий форм
	 */

	public static final String TABLE_NAME_SAVE_PARAMETERS = "search_phone_temp_table";

	/**
	 * Имя таблицы для хранения результатов поиска сервиса
	 */

	public static final String TABLE_NAME_SERVICE_SEARCH_RESULTS = "service";

	/**
	 * Имя таблицы для хранения запроса
	 */

	public static final String TABLE_NAME_SERVICE_SEARCH_SQL_REQUEST = "SQL";

	/**
	 * Код, показывающий что активити вызывается через уведомление
	 */

	public static final int REQUEST_CODE_NOTIFOCATION = 1;
}
