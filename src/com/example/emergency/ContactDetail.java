package com.example.emergency;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emergency.bean.Signup;
import com.example.parser.Parser;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContactDetail extends ActionBarActivity {

	private Button activity_signupbtn;
	private EditText activity_contactone;
	private EditText activity_contacttwo;
	private EditText activity_contactthree;
	private Signup signup;
	SessionManager session;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		session = new SessionManager(this);
		Intent i =  getIntent();
		Bundle b =  i.getExtras();
		signup = (Signup)b.getSerializable("reg");
		
		setContentView(R.layout.activity_contact_detail);
		ActionBar ab = getActionBar();
		ab.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.parseColor("#D62741")));
		ab.setTitle("Contacts");

		ab.show();

		activity_signupbtn = (Button) findViewById(R.id.activity_signupbtn);
		activity_contactone = (EditText) findViewById(R.id.activity_contactone);
		activity_contacttwo = (EditText) findViewById(R.id.activity_contacttwo);
		activity_contactthree = (EditText) findViewById(R.id.activity_contactthree);

	}

	public void submitall() {
		String contactone = activity_contactone.getText().toString();
		String contacttwo = activity_contacttwo.getText().toString();
		String contactthree = activity_contactthree.getText().toString();
		
		session.setNo1(contactone);
		
		signup.setContactone(contactone);
		signup.setContacttwo(contacttwo);
		signup.setContactthree(contactthree);
		RegisterTaskone taskone = new RegisterTaskone();
		taskone.execute(signup);
		Intent i = new Intent(ContactDetail.this, Selectmode.class);
		startActivity(i);
		
		
	}

	class RegisterTaskone extends AsyncTask<Signup, Void, JSONObject> {
		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(ContactDetail.this);
			pd.setMessage("Loading....");
			pd.show();

		}

		@Override
		protected JSONObject doInBackground(Signup... params) {
			try {
				return Parser.sendRegister(params[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject result) {

			// check for true / false
			pd.cancel();
			pd.dismiss();
		}

	}

	public void contactvalidate(View view) {

		if (activity_contactone.getText().length() == 0) {
			activity_contactone.setError("please enter valid username");
		}

		else if (activity_contacttwo.getText().length() == 0) {
			activity_contacttwo.setError("please enter valid address");
		}

		else if (activity_contactthree.getText().length() == 0) {
			activity_contactthree.setError("please enter valid email");
		} else {
			submitall();
		}

	}

}
