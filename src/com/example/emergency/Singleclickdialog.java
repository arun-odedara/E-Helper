package com.example.emergency;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class Singleclickdialog extends DialogFragment {

	String selection;
	static CharSequence[] items = { "ANDHRA PRADESH", "ARUNACHAL PRADESH",
			"ASSAM", "BIHAR", "CHATTISGARH", "GOA", "GUAJRAT", "HARYANA",
			"HIMACHAL PRADESH", "JAMMU KASHMIR", "JARKHAND", "KARNATAK",
			"KERALA", "MADHYAPRADESH", "MAHARASTRA", "MANIPUR", "MEGHALAY",
			"MIZORAM", "NAGALAND", "ODISHA", "PUNJAB", "RAJASTHAN", "SIKKIM",
			"TAMIL NADU", "TALANGANA", "TRIPURA", "UTTAR PRADESH",
			"UTTARAKHAND", "WEST BENGAL" };

	private OnSelectedState onSelectedState;

	public OnSelectedState getOnSelectedState() {
		return onSelectedState;
	}

	public void setOnSelectedState(OnSelectedState onSelectedState) {
		this.onSelectedState = onSelectedState;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Select State")
				.setSingleChoiceItems(items, -1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						switch (arg1) {
						case 0:
							selection = (String) items[arg1];
							break;
						case 1:
							selection = (String) items[arg1];
							break;
						case 2:
							selection = (String) items[arg1];
							break;
						case 3:
							selection = (String) items[arg1];
							break;
						case 4:
							selection = (String) items[arg1];
							break;
						case 5:
							selection = (String) items[arg1];
							break;
						case 6:
							selection = (String) items[arg1];
							break;
						case 7:
							selection = (String) items[arg1];
							break;
						case 8:
							selection = (String) items[arg1];
							break;
						case 9:
							selection = (String) items[arg1];
							break;
						case 10:
							selection = (String) items[arg1];
							break;
						case 11:
							selection = (String) items[arg1];
							break;
						case 12:
							selection = (String) items[arg1];
							break;
						case 13:
							selection = (String) items[arg1];
							break;
						case 14:
							selection = (String) items[arg1];
							break;
						case 15:
							selection = (String) items[arg1];
							break;
						case 16:
							selection = (String) items[arg1];
							break;
						case 17:
							selection = (String) items[arg1];
							break;
						case 18:
							selection = (String) items[arg1];
							break;
						case 19:
							selection = (String) items[arg1];
							break;
						case 20:
							selection = (String) items[arg1];
							break;
						case 21:
							selection = (String) items[arg1];
							break;
						case 22:
							selection = (String) items[arg1];
							break;
						case 23:
							selection = (String) items[arg1];
							break;
						case 24:
							selection = (String) items[arg1];
							break;
						case 25:
							selection = (String) items[arg1];
							break;
						case 26:
							selection = (String) items[arg1];
							break;
						case 27:
							selection = (String) items[arg1];
							break;
						case 28:
							selection = (String) items[arg1];
							break;

						}

					}
				})
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						Toast.makeText(getActivity(),
								"Your State is: " + selection,
								Toast.LENGTH_SHORT).show();
						onSelectedState.onSelectedState(selection);
					}
				});
		return builder.create();
	}

	interface OnSelectedState {
		public void onSelectedState(String name);
	}

}
