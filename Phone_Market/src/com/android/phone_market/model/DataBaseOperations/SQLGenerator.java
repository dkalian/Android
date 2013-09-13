package com.android.phone_market.model.DataBaseOperations;

/**
 * @author Николай
 *
 */
public class SQLGenerator {

	/**
	 * @param brand брэнд
	 * @param model модель
	 * @param color цвет
	 * @param state состяние
	 * @param region регион
	 * @param price цена
	 * @param bcolor цвет важен/неважен
	 * @param bstate состояние важено/неважено
	 * @param bregion регион важен/неважен
	 * @return String SQL запрос
	 */
	public static String Generate(String brand, String model,
			String color, String state, String region, String price,
			boolean bcolor, boolean bstate, boolean bregion) {
		
		String SQLRequest = "SELECT * FROM market_list WHERE Upper(`brand`) = Upper('"
				+ brand + "') and Upper(`model`) = Upper('" + model + "')";

		if (bcolor == true) {
			SQLRequest = SQLRequest + " and Upper(`color`) = Upper('" + color
					+ "')";
		}

		if (bstate == true) {
			SQLRequest = SQLRequest + " and Upper(`state`) = Upper('" + state
					+ "')";
		}

		if (bregion == true) {
			SQLRequest = SQLRequest + " and Upper(`region`) = Upper('" + region
					+ "')";
		}
		SQLRequest = SQLRequest + " and cast((price) as UNSIGNED) <= cast(("
				+ price + ") as UNSIGNED)";
		SQLRequest = SQLRequest + ";";
		
		
		return SQLRequest;

	}
	
	/**
	 * SQL запрос на добавление в базу
	 * 
	 * @param brand
	 *            брэнд
	 * @param model
	 *            модель
	 * @param color
	 *            цвет
	 * @param more_info
	 *            больше информации
	 * @param state
	 *            состояние
	 * @param price
	 *            цена
	 * @param name
	 *            Имя
	 * @param region
	 *            регион
	 * @param phone_number
	 *            номер телефона
	 * @param other_phone_number
	 *            дополнительный номер телефона
	 * @param e_mail
	 *            электронная почта
	 * @param EMEI
	 * @return String SQL
	 */
	public static String Generate(String brand, String model, String color,
			String more_info, String state, String price, String name,
			String region, String phone_number, String other_phone_number,
			String e_mail, String EMEI) {
		
		String SQLRequest = "INSERT INTO market_list (brand,model,color,more_info,state,price,name,region,phone_number,other_phone_number,e_mail, EMEI) "
				+ "VALUES ('"
				+ brand
				+ "','"
				+ model
				+ "','"
				+ color
				+ "','"
				+ more_info
				+ "','"
				+ state
				+ "','"
				+ price
				+ "','"
				+ name
				+ "','"
				+ region
				+ "','"
				+ phone_number
				+ "','"
				+ other_phone_number
				+ "','"
				+ e_mail + "','" + EMEI + "');";
		
		return SQLRequest;

	}
}
