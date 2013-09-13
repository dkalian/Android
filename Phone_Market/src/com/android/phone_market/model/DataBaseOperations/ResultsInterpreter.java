package com.android.phone_market.model.DataBaseOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Николай
 * 
 */
public class ResultsInterpreter {
	/**
	 * @param resultset ResultSet
	 * @return ArrayList(ArrayList((String))
	 */
	public static ArrayList<ArrayList<String>> Interpretate(ResultSet resultset) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

		try {
			if ((resultset.first())) {
				do {
					String colId = resultset.getString("id");
					String colBrand = resultset.getString("brand")
							.toUpperCase();
					String colModel = resultset.getString("model");
					String colColor = resultset.getString("color");
					String colMoreInfo = resultset.getString("more_info");
					String colState = resultset.getString("state");
					String colPrice = resultset.getString("price");
					String colName = resultset.getString("name");
					String colRegion = resultset.getString("region");
					String colPhoneNumber = resultset.getString("phone_number");
					String colOtherPhoneNumber = resultset
							.getString("other_phone_number");
					String colEMail = resultset.getString("e_mail");
					ArrayList<String> tempresults = new ArrayList<String>();

					/*
					 * Основные данные
					 */

					tempresults.add(colBrand + " " + colModel); // 0
					tempresults.add(colState); // 1
					tempresults.add(colPrice); // 2
					tempresults.add(colRegion); // 3

					/*
					 * Основные данные
					 */

					/*
					 * Побочные данные
					 */

					tempresults.add(colId); // 4
					tempresults.add(colMoreInfo); // 5
					tempresults.add(colName); // 6
					tempresults.add(colPhoneNumber); // 7
					tempresults.add(colOtherPhoneNumber); // 8
					tempresults.add(colEMail); // 9
					tempresults.add(colColor); // 10

					/*
					 * Побочные данные
					 */
					results.add(tempresults);
				} while (resultset.next());
			} else {
				// Результатов нет

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}
}
