package com.ussd.quickussd.model;

/**
 * @author Nickolai
 *
 */
public class USSDCodePreparer {

	private static String code = "";
	private static String ussd = "";

	/**
	 * @param bank �������� �����
	 * @param account ����� ����� (4 ��������� �����)
	 * @return USSD
	 */
	public static String prepeare(String bank, String account) {
		for (int i = 0; i < Constant.BANKS.length; i++) {
			if (bank.equalsIgnoreCase("���������")) {
				code = "*212";
				ussd = code + account;
				break;
			}
			if (bank.equalsIgnoreCase("�������������")) {
				code = "*146";
				ussd = code + account;
				break;
			}
		}
		return ussd;
	}
}
