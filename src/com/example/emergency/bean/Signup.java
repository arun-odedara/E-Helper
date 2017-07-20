package com.example.emergency.bean;

import java.io.Serializable;



public class Signup implements Serializable{
	private int userid;
	private String email;
	private String address;
	private String mobileno;
	private String username;
	private String contactone;
	private String contacttwo;
	private String contactthree;
	private String state;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContactone() {
		return contactone;
	}
	public void setContactone(String contactone) {
		this.contactone = contactone;
	}
	public String getContacttwo() {
		return contacttwo;
	}
	public void setContacttwo(String contacttwo) {
		this.contacttwo = contacttwo;
	}
	public String getContactthree() {
		return contactthree;
	}
	public void setContactthree(String contactthree) {
		this.contactthree = contactthree;
	}
	public Signup()	{
		
	}
	public Signup(String email, String address, String mobileno, String username, String state) {
		super();
		this.email = email;
		this.address = address;
		this.mobileno = mobileno;
		this.username = username;
		this.state = state;
	}
	public Signup(int userid, String email, String address, String mobileno,
			String username) {
		super();
		this.userid = userid;
		this.email = email;
		this.address = address;
		this.mobileno = mobileno;
		this.username = username;
	}
	public Signup(String contactone, String contacttwo, String contactthree) {
		super();
		this.contactone = contactone;
		this.contacttwo = contacttwo;
		this.contactthree = contactthree;
	}
	public String toString() {
		return "Signup [userid=" + userid + ", username=" + username + ", email=" + email	+ 
				", address=" + address + ", mobileno=" + mobileno+ " , contactone="+ contactone+","
						+ " contacttwo="+ contacttwo+", contactthree="+ contactthree+" ]";

	}
}

