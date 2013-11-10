package com.ussd.quickussd.model.CustomAlertDialogs;

import android.app.AlertDialog;
import android.content.Context;

/**
 * @author Nickolai
 *
 */
public class MyAlertDialog {

	static AlertDialog dialog;

	/**
	 * ����� �� ����� �������
	 * 
	 * @param context
	 *            ��������
	 * 
	 * @param Title
	 *            ��������
	 * @param Message
	 *            ���������
	 */

	public static void Show(Context context, String Title, String Message) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle(Title);
		adb.setMessage(Message);
		adb.setPositiveButton("Ok", null);
		dialog = adb.create();
		dialog.show();
	}
}