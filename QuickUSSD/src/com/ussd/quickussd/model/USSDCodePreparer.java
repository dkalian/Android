package com.ussd.quickussd.model;

/**
 * @author Nickolai
 *
 */
public class USSDCodePreparer {

	private static String code = "";
	private static String ussd = "";

	/**
	 * @param bank Название банка
	 * @param account Номер счета (4 последние цифры)
	 * @return USSD
	 */
	public static String prepeare(String bank, String account) {
		for (int i = 0; i < Constant.BANKS.length; i++) {
			if (bank.equalsIgnoreCase("Приорбанк")) {
				code = "*212";
				ussd = code + account;
				break;
			}
			if (bank.equalsIgnoreCase("Белинвестбанк")) {
				code = "*146";
				ussd = code + account;
				break;
			}
		}
		return ussd;
	}
}
