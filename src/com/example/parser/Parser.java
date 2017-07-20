package com.example.parser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.constant.Constant;
import com.example.emergency.bean.Signup;




public class Parser {
	

		public static JSONObject getJSON(String url) throws Exception {

			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			HttpEntity enitity = response.getEntity();
			String json = EntityUtils.toString(enitity);
			System.out.println(json);
			return new JSONObject(json);

		}
		
		/*****************************************************************************/
		
		
		public static JSONObject sendRegister(Signup signup) throws Exception{
			String url = Constant.registerURL(signup);
			return getJSON(url);
			
		}

		public static JSONObject sendLocation(
				com.example.emergency.bean.Location location) throws Exception {
			String url = Constant.locationURL(location);
			return getJSON(url);
			
		}

		

	

}
