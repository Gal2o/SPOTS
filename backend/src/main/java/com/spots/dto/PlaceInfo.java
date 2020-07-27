package com.spots.dto;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class PlaceInfo {
	private int uid;
	private String place_name;
	private int price;
	private String address;
	private int code;
	private SimpleDateFormat open = new SimpleDateFormat ( "HH:mm:ss");
	private SimpleDateFormat close = new SimpleDateFormat ( "HH:mm:ss");
	private String lng;
	private String lat;
	public PlaceInfo(int uid, String place_name, int price, String address, int code, SimpleDateFormat open,
			SimpleDateFormat close, String lng, String lat) {
		super();
		this.uid = uid;
		this.place_name = place_name;
		this.price = price;
		this.address = address;
		this.code = code;
		this.open = open;
		this.close = close;
		this.lng = lng;
		this.lat = lat;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public SimpleDateFormat getOpen() {
		return open;
	}
	public void setOpen(SimpleDateFormat open) {
		this.open = open;
	}
	public SimpleDateFormat getClose() {
		return close;
	}
	public void setClose(SimpleDateFormat close) {
		this.close = close;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "PlaceInfo [uid=" + uid + ", place_name=" + place_name + ", price=" + price + ", address=" + address
				+ ", code=" + code + ", open=" + open + ", close=" + close + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
}
