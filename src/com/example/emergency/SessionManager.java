package com.example.emergency;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

	private String no1;
	private static final String PREF = "emergancy";
	private static final String NO1 = "no1";
	Context context;
	private SharedPreferences sp;
	private Editor edit;

	public SessionManager(Context context) {
		this.context = context;
		sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
		edit = sp.edit();
	}

	public String getNo1() {
		return sp.getString(NO1, "");
	}

	public void setNo1(String no1) {
		edit.putString(NO1, no1);
		edit.commit();
	}

}
