package com.ussd.quickussd.model.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ussd.quickussd.R;
import com.ussd.quickussd.model.Constant;
import com.ussd.quickussd.model.CustomAlertDialogs.MyAlertDialog;
import com.ussd.quickussd.model.DataBaseOperations.DataBaseConstant;
import com.ussd.quickussd.model.DataBaseOperations.DataBaseOperation;

/**
 * @author Nickolai
 * @version 0.0.1
 */
public class CardsAdapter extends BaseAdapter {

	/* константы определяют наши textView для вывода данных */

	private static final int KEEPER_NAME = R.id.item_card_keeper_text;
	private static final int ACCOUNT_NUMBER = R.id.item_card_account_number_text;
	private static final int ACTUAL_DATE_TEXT = R.id.item_card_actual_date_text;
	private static final int ABOUT_CARD_TEXT = R.id.item_card_about_card_text;
	private static final int BANK_TEXT = R.id.item_card_bank_text;
	private static final int BUTTON_REMOVE_CARD = R.id.item_card_button_remove;

	private static final int RESOURCE = R.layout.item_card;

	private Context context;
	private ArrayList<HashMap<String, Object>> cardsList;

	/**
	 * Адаптер для списка: сайты в акаунте
	 * 
	 * @param cardsList
	 *            ArrayList<HashMap<String, Object>>
	 * @param context
	 *            Activity context
	 */
	public CardsAdapter(ArrayList<HashMap<String, Object>> cardsList,
			Context context) {
		this.context = context;
		this.cardsList = cardsList;
	}

	@Override
	public int getCount() {
		return cardsList.size();
	}

	@Override
	public Object getItem(int position) {
		return cardsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private TextView text_keeper_name;
	private TextView text_account_number;
	private TextView text_actual_date;
	private TextView bank;
	private TextView about_card;
	private ImageButton button_remove_card;

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			v = inflater.inflate(RESOURCE, null);

		}

		text_keeper_name = (TextView) v.findViewById(KEEPER_NAME);
		text_account_number = (TextView) v.findViewById(ACCOUNT_NUMBER);
		text_actual_date = (TextView) v.findViewById(ACTUAL_DATE_TEXT);
		bank = (TextView) v.findViewById(BANK_TEXT);
		about_card = (TextView) v.findViewById(ABOUT_CARD_TEXT);

		button_remove_card = (ImageButton) v.findViewById(BUTTON_REMOVE_CARD);

		HashMap<String, Object> cardsMap = new HashMap<String, Object>();
		cardsMap = (HashMap<String, Object>) cardsList.get(position);
		button_remove_card.setFocusable(false);

		text_keeper_name.setText(cardsMap.get(
				DataBaseConstant.HASH_MAP_CARDS_INFO_KEEPER_KEY).toString());

		text_account_number
				.setText("*"
						+ cardsMap
								.get(DataBaseConstant.HASH_MAP_CARDS_INFO_ACCOUNT_NUMBER_KEY)
								.toString());

		if (cardsMap.get(DataBaseConstant.HASH_MAP_CARDS_ABOUT_CARD_KEY)
				.toString().length() != 0) {
			about_card.setText(cardsMap.get(
					DataBaseConstant.HASH_MAP_CARDS_ABOUT_CARD_KEY).toString());
		} else {
			about_card.setText("Не указано");

		}
		if (cardsMap.get(DataBaseConstant.HASH_MAP_CARDS_INFO_ACTUAL_DATE_KEY)
				.toString().length() != 0) {
			text_actual_date.setText(cardsMap.get(
					DataBaseConstant.HASH_MAP_CARDS_INFO_ACTUAL_DATE_KEY)
					.toString());
		} else {
			text_actual_date.setText("Не указано");

		}

		bank.setText((cardsMap
				.get(DataBaseConstant.HASH_MAP_CARDS_INFO_BANK_KEY).toString()));

		button_remove_card.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("",
						"Remove "
								+ cardsList
										.get(position)
										.get(DataBaseConstant.HASH_MAP_CARDS_INFO_ID_KEY)
										.toString());
				if (DataBaseOperation
						.RemoveCard(
								context,
								cardsList
										.get(position)
										.get(DataBaseConstant.HASH_MAP_CARDS_INFO_ID_KEY)
										.toString())) {
					System.out.println("Remove element. Position:" + position);
					cardsList.remove(position);
					System.out.println(cardsList);
					notifyDataSetChanged();
					Log.d("", "Removed");

				} else {
					MyAlertDialog.Show(context, "Ошибка",
							Constant.ERROR_MESSAGE_REMOVE_FAILED);
				}
			}
		});
		return v;
	}
}