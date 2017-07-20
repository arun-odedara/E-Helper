package com.example.parser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
public class JSONParser {

	public static JSONObject parseJSON(String url) {

		JSONObject jsonObject = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpGet = new HttpPost(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			
			HttpEntity httpEntity =  response.getEntity();
			String data = EntityUtils.toString(httpEntity); // Converstion to String format from Entity
			
			jsonObject = new JSONObject(data);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}

}
