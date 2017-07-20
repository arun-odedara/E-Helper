package com.example.emergency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.constant.PoliceConstant;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

@SuppressLint("NewApi") public class PoliceList extends ActionBarActivity {

	private ListView policelist;
	List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
	private double latitude = 23.0300;
	private double longitude = 72.5800;
	private LocationManager manager;
	private long minTime = 10 * 1000; // mili seconds
	private float minDistance = 3 * 100; // meter
	private ListView policestations;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_police_list);

		ActionBar ab = getActionBar();
		ab.setTitle("police");
		ab.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(
				Color.parseColor("#D62741")));
		ab.show();

		policestations = (ListView) findViewById(R.id.policestations);
		
		locationFetching();
	}

	public void locationFetching() {

		// Location Listner for GPS traking...

		LocationListener listener = new LocationListener() {

			private double latitude;

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
				policeLocation pl = new policeLocation();
				pl.execute(latitude, longitude);

			}
		};
		// To track the locations...
		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,
				minDistance, listener);

		policeLocation pl = new policeLocation();
		pl.execute(latitude, longitude);
		

	}

	class policeLocation extends AsyncTask<Double, Void, JSONObject> {
		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			data.clear();
			pd = new ProgressDialog(PoliceList.this);
			pd.setMessage("Loading....");
			pd.show();

		}

		@Override
		protected void onPostExecute(JSONObject result) {

			try {
			
				JSONArray array = result.getJSONArray("results");
				for (int i = 0; i < array.length(); i++) {
					JSONObject o = array.getJSONObject(i);
					String name = o.getString("name");
					String vicinity = o.getString("vicinity");
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("vicinity", vicinity);
					map.put("name", name);
					data.add(map);

				}

				int resource = R.layout.policestation;
				String[] from = { "name", "vicinity" };
				int[] to = { R.id.policename, R.id.policeaddress };
				SimpleAdapter adapter = new SimpleAdapter(PoliceList.this,
						data, resource, from, to);

				policestations.setAdapter(adapter);
			} catch (Exception e) {
				e.printStackTrace();
			}

			pd.cancel();
			pd.dismiss();
		}

		@Override
		protected JSONObject doInBackground(Double... params) {
			// TODO Auto-generated method stub
			return PoliceConstant.getPoliceStation(params[0], params[1]);
		}

	}

}
