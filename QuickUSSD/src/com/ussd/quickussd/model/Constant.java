package com.ussd.quickussd.model;

/**
 * @author Nickolai
 * 
 */
public class Constant {
	/**
	 * Список банков
	 */
	public static final String[] BANKS = { "Приорбанк", "Белинвестбанк"};
	/**
	 * Текст ошибки при error_code = 1
	 */
	public static final String ERROR_INSERT_TO_CV = "При добавлении карточки возникла ошибка.  Перепроверьте данные и повторите попытку.";
	/**
	 * Текст ошибки при error_code = 2
	 */
	public static final String ERROR_INSERT = "При добавлении карточки в базу возникла ошибка. Перепроверьте данные и повторите попытку.";
	
	/**
	 * Текст ошибки при неудачном удалении карточки из БД
	 */
	
	public static final String ERROR_MESSAGE_REMOVE_FAILED = "При далении карточки возникла ошибка. Повторите попытку позже.";
}
