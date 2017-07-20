package com.example.constant;

import com.example.emergency.bean.Location;
import com.example.emergency.bean.Signup;



public final class Constant {

	public static final String APPLICATION_URL = "http://topsiqs-topstechnologies.rhcloud.com/android/";
	public static final String PAGE_URL = APPLICATION_URL + "control.jsp?";

	public static final String TABLE_KEY = "table=";
	public static final String METHOD_ADD = "&method=add";
	public static final String METHOD_UPDATE = "&method=update";
	public static final String METHOD_DELETE = "&method=delete";
	public static final String METHOD_LIST = "&method=list";
	public static final String METHOD_FIND = "&method=find";
	public static final String FIELD_KEY = "&fields=";
	/******************************************************************************/
	public static final String REG_TABLE = "helpersignup";
	public static final String REG_LOCATIONTABLE = "helperlocation";

	
	public static final String COL_USERID = "userid";
	public static final String COL_EMAIL = "email";
	public static final String COL_USERNAME = "username";
	public static final String COL_ADDRESS = "address";
	public static final String COL_MOBILENO = "mobileno";
	public static final String COL_STATE = "state";
	public static final String COL_CONTACTONE = "contactone";
	public static final String COL_CONTACTTWO = "contacttwo";
	public static final String COL_CONTACTTHREE = "contactthree";
	public static final String COL_LATITUDE = "latitude";
	public static final String COL_LONGITUDE = "longitude";
	public static final String COL_CURRENTADDRESS = "currentaddress";
	
	
	public static final String REG_USERID = "&userid=";
	public static final String REG_EMAIL = "&email=";
	public static final String REG_USERNAME = "&username=";
	public static final String REG_ADDRESS = "&address=";
	public static final String REG_MOBILENO = "&mobileno=";
	public static final String REG_STATE = "&state=";
	public static final String REG_CONTACTONE = "&contactone=";
	public static final String REG_CONTACTTWO = "&contacttwo=";
	public static final String REG_CONTACTTHREE = "&contactthree=";
	public static final String REG_LATITUDE = "&latitude=";
	public static final String REG_LONGITUDE = "&longitude=";
	public static final String REG_CURRENTADDRESS = "&currentaddress=";
	

	public static final String registerURL(Signup signup) {

		String username = signup.getUsername();
		String mobileno = signup.getMobileno();
		String email =signup.getEmail();
		String address = signup.getAddress();
		String state = signup.getState();
		String contactone = signup.getContactone();
		String contacttwo = signup.getContacttwo();
		String contactthree = signup.getContactthree();
		String url = PAGE_URL + TABLE_KEY + REG_TABLE + METHOD_ADD
				+ REG_USERNAME + username + REG_EMAIL + email + REG_ADDRESS + address + REG_MOBILENO + mobileno
				+ REG_STATE + state + REG_CONTACTONE  + contactone + REG_CONTACTTWO + contacttwo + REG_CONTACTTHREE + contactthree;
		url = url.replace(" ", "+");
		System.out.println(url);
		return url;
		
	}


	public static String locationURL(Location location) {
		Double latitude = location.getLat();
		Double longitude = location.getLang();
		String currentaddress = location.getCurrentaddress();
		String url = PAGE_URL + TABLE_KEY + REG_LOCATIONTABLE + METHOD_ADD + REG_LATITUDE + latitude + REG_LONGITUDE + longitude +  
				REG_CURRENTADDRESS + currentaddress;
		url = url.replace(" ", "+");
		System.out.println(url);
		return url;
	}
	
	
}
