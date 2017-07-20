package com.example.emergency;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emergency.Singleclickdialog.OnSelectedState;
import com.example.emergency.bean.Signup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FirstScreen extends FragmentActivity {

	private View sgn;
	private View name;
	private View addr;
	private View email;
	private View cntry;
	private View city;
	private ImageView image;
	String state = "";
	boolean completed;
	SharedPreferences sharedPrefs;

	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	private EditText activity_signup_username;
	private EditText activity_signup_email;
	private EditText activity_signup_address;
	private EditText activity_signup_mobileno;
	private Button activity_signup_nextbtn;
	private TextView activity_signup_selectedstate;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_screen);

		/*
		 * completed = sharedPrefs.getBoolean("complete", false); if (completed
		 * == true) { Intent intent = new Intent();
		 * intent.setClass(FirstScreen.this, Selectmode.class);
		 * startActivity(intent); finish(); }
		 */

		ActionBar ab = getActionBar();
		ab.setTitle("Signup");
		ab.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(
				Color.parseColor("#D62741")));
		ab.show();

		activity_signup_username = (EditText) findViewById(R.id.activity_signup_username);
		activity_signup_email = (EditText) findViewById(R.id.activity_signup_email);
		activity_signup_address = (EditText) findViewById(R.id.activity_signup_address);
		activity_signup_mobileno = (EditText) findViewById(R.id.activity_signup_mobileno);
		activity_signup_selectedstate = (TextView) findViewById(R.id.activity_signup_selectedstate);
		activity_signup_nextbtn = (Button) findViewById(R.id.activity_signup_nextbtn);

	}

	public void submit() {
		String username = activity_signup_username.getText().toString();
		String email = activity_signup_email.getText().toString();
		String address = activity_signup_address.getText().toString();
		String mobileno = activity_signup_mobileno.getText().toString();
		Signup signup = new Signup(email, address, mobileno, username, state);
		Intent i = new Intent(FirstScreen.this, ContactDetail.class);
		i.putExtra("reg", signup);
		startActivity(i);
	}

	public void selectstate(View v) {
		Singleclickdialog mydialog = new Singleclickdialog();
		mydialog.show(getSupportFragmentManager(), "mydialog");
		mydialog.setOnSelectedState(new OnSelectedState() {

			@Override
			public void onSelectedState(String name) {
				state = name;
			}
		});
		activity_signup_selectedstate.setText(mydialog.selection);
	}

	public void firstvalidate(View view) {
		
		
		if (activity_signup_username.getText().toString().length() == 0
				|| activity_signup_email.getText().toString().length() == 0
				|| activity_signup_address.getText().toString().length() == 0
				|| activity_signup_mobileno.getText().toString().length() == 0)
		{
			activity_signup_username.setError("Username is not valid");
			activity_signup_email.setError("email is not valid");
			activity_signup_address.setError("address is not valid");
			activity_signup_mobileno.setError("mobileno is not valid");
			
		}
		else
		{
			submit();
		}
}
}