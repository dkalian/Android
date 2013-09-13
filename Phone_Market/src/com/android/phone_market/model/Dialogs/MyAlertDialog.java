package com.android.phone_market.model.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

/**
 * @author Николай
 * 
 */
public abstract class MyAlertDialog {

	static AlertDialog dialog;

	/**
	 * Вывод на экран диалога
	 * 
	 * @param context
	 *            контекст
	 * 
	 * @param Title
	 *            Название
	 * @param Message
	 *            Сообщение
	 */

	public static void Show(Context context, String Title, String Message) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle(Title);
		adb.setMessage(Message);
		adb.setPositiveButton("Ok", null);
		dialog = adb.create();
		dialog.show();
	}

	/**
	 * @param context Activity context
	 * @param Title заголовок
	 * @param Message сообщение
	 * @param PositiveButtonText текст кнопки "да"
	 * @param NegativeButtonText текст кнопки "нет"
	 * @param OnPositiveClisckListner обрботчик нажатия на "да"
	 * @param OnNegativeClisckListner обрботчик нажатия на "нет"
	 */
	public static void Show(Context context, String Title, String Message,
			String PositiveButtonText, String NegativeButtonText,
			OnClickListener OnPositiveClisckListner,
			OnClickListener OnNegativeClisckListner) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle(Title);
		adb.setMessage(Message);
		adb.setPositiveButton(PositiveButtonText, OnPositiveClisckListner);
		adb.setNegativeButton(NegativeButtonText, OnNegativeClisckListner);
		dialog = adb.create();
		dialog.show();
	}
}
