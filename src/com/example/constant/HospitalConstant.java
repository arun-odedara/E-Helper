package com.example.constant;

import org.json.JSONObject;

import com.example.parser.JSONParser;

public class HospitalConstant {
	
	
	public static JSONObject getHospital(double lat, double lang) {

		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
				+ lat
				+ ","
				+ lang
				+ "&radius=500&types=hospital&key=AIzaSyCiq42kIDFwgPa1iBGInw3xkj7bs4rMY08";
		return JSONParser.parseJSON(url);
	}


}
