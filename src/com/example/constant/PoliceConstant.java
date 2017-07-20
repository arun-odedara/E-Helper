
package com.example.constant;

import org.json.JSONObject;

import com.example.parser.JSONParser;

public class PoliceConstant {

	public static JSONObject getPoliceStation(double lat, double lang) {

		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
				+ lat
				+ ","
				+ lang
				+ "&radius=1000&types=police&key=AIzaSyCiq42kIDFwgPa1iBGInw3xkj7bs4rMY08";
		return JSONParser.parseJSON(url);
	}

}
