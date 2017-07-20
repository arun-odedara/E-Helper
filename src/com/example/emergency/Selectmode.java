package com.example.emergency;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.parser.JSONParser;
import com.example.parser.Parser;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Selectmode extends ActionBarActivity {

	private MediaRecorder myAudioRecorder;
	private String outputFile = null;
	NotificationManager NM;
	private double latitude = 23.0300;
	private double longitude = 72.5800;
	private LocationManager manager;
	LocationListener listener;
	private long minTime = 10 * 1000; // mili seconds
	private float minDistance = 3 * 100; // meter
	private String API_KEY = "AIzaSyCiq42kIDFwgPa1iBGInw3xkj7bs4rMY08";
	private String URI = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
			+ latitude + "," + longitude + "&key=" + API_KEY;

	private String results = "results";
	private String status = "status";
	private String formatted_address = "formatted_address";
	SessionManager session;

	String address;
	private Button police;
	private Button hospital;

	// alarmmanager
	final static private long ONE_SECOND = 1000;
	final static private long TWENTY_SECONDS = ONE_SECOND * 10;
	PendingIntent pi;
	BroadcastReceiver br;
	AlarmManager am;
	private Button click;
	private ToggleButton red;
	private ToggleButton green;
	private ToggleButton yellow;

	//

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectmode);
		session = new SessionManager(this);
		red = (ToggleButton) findViewById(R.id.red);
		green = (ToggleButton) findViewById(R.id.green);
		yellow = (ToggleButton) findViewById(R.id.yellow);
		police = (Button) findViewById(R.id.button4);
		hospital = (Button) findViewById(R.id.button5);

		// recording

		green.setEnabled(false);
		outputFile = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/myrecording.3gp";
		myAudioRecorder = new MediaRecorder();
		myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myAudioRecorder.setOutputFile(outputFile);
		//

		ActionBar ab = getActionBar();
		ab.setTitle("Modes");
		ab.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(
				Color.parseColor("#D62741")));
		ab.show();

		/********************************** red ***********************************/

		/*
		 * red.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * // here location fetching.... locationFetching();
		 * 
		 * Toast t = Toast.makeText(Selectmode.this, "red mode selected",
		 * Toast.LENGTH_SHORT); t.show();
		 * 
		 * recordingStart();
		 * 
		 * }
		 * 
		 * });
		 */
		red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					red.setClickable(false);
					yellow.setClickable(false);
					green.setChecked(false);
					green.setClickable(true);
					locationFetching();
					 NM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				        Notification notify=new Notification(android.R.drawable.stat_notify_more,"Red mode selected",System.currentTimeMillis());
				        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
				        
				        notify.setLatestEventInfo(getApplicationContext(), "Red mode is activated...", "",pending);
				        NM.notify(0, notify);
				        
				       			        
				        
				        
					Toast t = Toast.makeText(Selectmode.this,
							"red mode selected", Toast.LENGTH_SHORT);
					t.show();

					recordingStart();

				} else {
					yellow.setClickable(true);
					green.setClickable(false);
				}
			}
		});

		/****************** yellow ***********************/

		/*
		 * yellow.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Toast t =
		 * Toast.makeText(Selectmode.this, "yellow mode selected",
		 * Toast.LENGTH_SHORT); t.show();
		 * 
		 * setup();
		 * 
		 * am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		 * SystemClock.elapsedRealtime() + TWENTY_SECONDS, pi);
		 * 
		 * } });
		 */
		yellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					yellow.setClickable(false);
					red.setClickable(false);
					green.setChecked(false);
					green.setClickable(true);
					
						/*NM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				        Notification notify=new Notification(android.R.drawable.stat_notify_more,"yellow mode selected",System.currentTimeMillis());
				        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
				        notify.setLatestEventInfo(getApplicationContext(), "yellow mode is activated...", "",pending);
				        NM.notify(0, notify);
				        */
						
			       
					Toast t = Toast.makeText(Selectmode.this,
							"yellow mode selected", Toast.LENGTH_SHORT);
					t.show();

					setup();

					am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
							SystemClock.elapsedRealtime() + TWENTY_SECONDS, pi);

				} else {
					
					red.setClickable(true);
					green.setClickable(false);
				}
			}
		});
		
		
		/***************** green ********************/

		/*
		 * green.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * Toast t = Toast.makeText(Selectmode.this, "green mode selected",
		 * Toast.LENGTH_SHORT); t.show();
		 * 
		 * recordingStop();
		 * 
		 * manager.removeUpdates(listener); manager = null;
		 * 
		 * }
		 * 
		 * });
		 */
		green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Toast t = Toast.makeText(Selectmode.this,
							"green mode selected", Toast.LENGTH_SHORT);
					t.show();
					NM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
			        Notification notify=new Notification(android.R.drawable.stat_notify_more,"Green mode selected",System.currentTimeMillis());
			        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
			        notify.setLatestEventInfo(getApplicationContext(), "Green mode is activated...", "",pending);
			        NM.notify(0, notify);
					recordingStop();

					manager.removeUpdates(listener);
					manager = null;

					/* restartActivity(); */
					red.setChecked(false);
					yellow.setChecked(false);
					red.setClickable(true);
					yellow.setClickable(true);

				}
				
			}
		});

		/***************** green mode ***********************/

		police.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Selectmode.this, PoliceList.class);
				startActivity(i);

			}
		});
		hospital.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Selectmode.this, HospitalList.class);
				startActivity(i);

			}
		});

	}

	public void restartActivity() {

		Toast.makeText(Selectmode.this, "activity refreshed", Toast.LENGTH_LONG)
				.show();

		Intent intent = getIntent();
		finish();
		startActivity(intent);

	}

	public void recordingStart() {

		try {
			myAudioRecorder.prepare();
			myAudioRecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		red.setEnabled(false);
		green.setEnabled(true);
		Toast.makeText(getApplicationContext(), "Recording started",
				Toast.LENGTH_LONG).show();
	}

	public void recordingStop() {

		myAudioRecorder.stop();
		myAudioRecorder.release();
		myAudioRecorder = null;
		green.setEnabled(false);

		Toast.makeText(getApplicationContext(), "Audio recorded successfully",
				Toast.LENGTH_LONG).show();

	}

	private void setup() {
		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {
				Toast.makeText(c, "Red mode selected", Toast.LENGTH_LONG)
						.show();
				red.setChecked(true);
				yellow.setChecked(false);
				recordingStart();
				locationFetching();

			}

		};
		registerReceiver(br, new IntentFilter("com.authorwjf.wakey"));
		pi = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.authorwjf.wakey"), 0);
		am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
	}

	public void locationFetching() {

		System.out.println("location fetching.................");

		// Location Listner for GPS traking...

		listener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {

				System.out.println("Provider : " + provider + " Status : "
						+ status);
			}

			@Override
			public void onProviderEnabled(String provider) {

				System.out.println("Provider Enabled : " + provider);
			}

			@Override
			public void onProviderDisabled(String provider) {

				System.out.println("Provider Disabled : " + provider);

			}

			@Override
			public void onLocationChanged(Location location) {

				latitude = location.getLatitude();
				longitude = location.getLongitude();
				Toast.makeText(getApplicationContext(),
						"lat : " + latitude + " Lang : " + longitude,
						Toast.LENGTH_LONG).show();
				// Load Class call....
				// Load Class call....
				Load load = new Load();
				load.execute(setURI(latitude, longitude));

			}
		};

		// To track the locations...
		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,
				minDistance, listener);

	}

	protected String setURI(double latitude, double longitude) {

		return "https://maps.googleapis.com/maps/api/geocode/json?latlng="
				+ latitude + "," + longitude + "&key=" + API_KEY;
	}

	public class Load extends AsyncTask<String, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(String... params) {
			JSONObject json = JSONParser.parseJSON(params[0]);
			System.out.println(json.toString());
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			try {
				String s = json.getString(status);
				System.out.println(s);
				if (s.equalsIgnoreCase("OK")) {

					JSONArray array = json.getJSONArray(results);
					// for(int i=0;i<array.length();i++){
					JSONObject object = array.getJSONObject(0);
					address = object.getString(formatted_address);
					System.out.println("<<<<<<<<" + address);
					// }
					Toast t = Toast.makeText(Selectmode.this,
							"Current location is: " + address,
							Toast.LENGTH_LONG);
					t.show();

					com.example.emergency.bean.Location location = new com.example.emergency.bean.Location(
							latitude, longitude, address);
					new SendLocation().execute(location);
					// send Sms

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}

	public void sendSMSMessage(double latitude, double longitude, String address) {
		Log.i("Send SMS", "");
		String message = "I am in danger....I need help.." + "Latitude : " + latitude + " Longitude : " + longitude
				+ " Address :" + address;

		try {
			SmsManager smsManager = SmsManager.getDefault();
			String phoneNo = session.getNo1();
			smsManager.sendTextMessage(phoneNo, null, message, null, null);
			Toast.makeText(getApplicationContext(), "SMS sent.",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS failed, please try again.", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	class SendLocation extends
			AsyncTask<com.example.emergency.bean.Location, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(
				com.example.emergency.bean.Location... params) {
			try {
				return Parser.sendLocation(params[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			sendSMSMessage(latitude, longitude, address);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		int GroupId = 0;
		int ItemId = Menu.FIRST;
		int OrderId = Menu.NONE;

		MenuItem m1 = menu.add(GroupId, ItemId, OrderId, "About");
		MenuItem m2 = menu.add(GroupId, ItemId + 1, OrderId + 1, "Red Mode");
		MenuItem m3 = menu.add(GroupId, ItemId + 1, OrderId + 1, "Green Mode");
		MenuItem m4 = menu.add(GroupId, ItemId + 1, OrderId + 1, "Yellow Mode");
		MenuItem m5 = menu.add(GroupId, ItemId + 1, OrderId + 1, "Refresh");

		m1.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Selectmode.this);
				StringBuilder sv = new StringBuilder();
				sv.append("Version : 1.0");
				sv.append("\n");
				sv.append("\n");
				sv.append("Developer:Rahul Gurjar");
				sv.append("\n");
				sv.append("                  Arun Odedara");
				sv.append("\n");
				sv.append("\n");
				sv.append("Email:rahulgurjar4606@yahoo.com");
				sv.append("\n");
				sv.append("           odedara.arun34@gmail.com");
				sv.append("\n");
				sv.append("\n");
				sv.append("Disclaimer:Before share apk,please make sure you have the redistribution right");
				sv.append("\n");

				// set title
				alertDialogBuilder.setTitle("About");

				// set dialog message
				alertDialogBuilder.setMessage(sv.toString());
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
				return false;

			}
		});

		m2.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Selectmode.this);

				// set title
				alertDialogBuilder.setTitle("Red Mode");
				StringBuilder sv = new StringBuilder();
				
				sv.append("-> When the user in danger situation and need the help then this mode is selected");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> When this mode will selected first of all voice recording will be started "
						+ "to gather the information in background.");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> User's current location is fetched based on latitude and langitude.from this latitude and longitude the user's"
						+ "current address will be created. ");
				sv.append("\n");
				sv.append("\n");
				sv.append("->the contacts which are provided by the user , this user's current address will sent using sms "
						+ "to those contacts"
						+ "and specifies that the user is in danger situation");

				// set dialog message
				alertDialogBuilder.setMessage(sv.toString());
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
				return false;

			}
		});
		m3.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Selectmode.this);

				// set title
				alertDialogBuilder.setTitle("Green Mode");
				
				StringBuilder sv = new StringBuilder();
				sv.append("-> this is the safe mode.");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> When user notifies that he/she is safe from the situation "
						+ "then this mode is selected by the user.");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> When this mode is selected another mode (red or yellow)is disabled and the services like voice recording , "
						+ "sms sending are stopped.");

				// set dialog message
				alertDialogBuilder.setMessage(sv.toString());
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
				return false;

			}
		});
		m4.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Selectmode.this);
				StringBuilder sv = new StringBuilder();
				// set title
				alertDialogBuilder.setTitle("Yellow Mode");
				// set dialog message
				sv.append("-> When any type doubtable situation but not happened anything yet choose yellow mode.");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> In this mode timer is set to 30 minute.");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> When user deactivate this mode before 30 min this mode will "
						+ "be disable and green mode is enabled which means user is safe.");
				sv.append("\n");
				sv.append("\n");
				sv.append("-> But if the user don't able to stop this timer because of any type of reason"
						+ " this mode will activate the red mode and the functionality of the red mode is done."
						+ "and the user id defined in danger situation");
				
				// set dialog message
				alertDialogBuilder.setMessage(sv.toString());
				AlertDialog alertDialog = alertDialogBuilder.create();
				
				//show it
				alertDialog.show();
				return false;

			}
		});
		m5.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				restartActivity();
				return false;

			}
		});
		return true;

	}

}
