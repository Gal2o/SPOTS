package com.spots.dto;

import java.util.Date;

public class TMatchInfo {
	private int uid;
	private int head_uid;
	private int home_team_uid;
	private int away_team_uid;
	private int home_matching_entry_uid;
	private int away_matching_entry_uid;
	private String create_date;
	private String matching_date;
	private int home_score;
	private int away_score;
	private int ready_num;
	private int place_uid;
	private int price;
	private int head_price;
	private String dong_code;
	private String title;
	private int mvp;
	private int manager_uid;
	
	
	
	public int getManager_uid() {
		return manager_uid;
	}

	public void setManager_uid(int manager_uid) {
		this.manager_uid = manager_uid;
	}

	public int getMvp() {
		return mvp;
	}

	public void setMvp(int mvp) {
		this.mvp = mvp;
	}

	public TMatchInfo() {}
	
	public TMatchInfo(int uid, int head_uid, int home_team_uid, int away_team_uid, int home_matching_entry_uid, int away_matching_entry_uid, String create_date,
			String matching_date, int home_score, int away_score, int ready_num, int place_uid, int price, int head_price,
			String dong_code, String title) {
		super();
		this.uid = uid;
		this.head_uid = head_uid;
		this.home_team_uid = home_team_uid;
		this.away_team_uid = away_team_uid;
		this.home_matching_entry_uid = home_matching_entry_uid;
		this.away_matching_entry_uid = away_matching_entry_uid;
		this.create_date = create_date;
		this.matching_date = matching_date;
		this.home_score = home_score;
		this.away_score = away_score;
		this.ready_num = ready_num;
		this.place_uid = place_uid;
		this.price = price;
		this.head_price = head_price;
		this.dong_code = dong_code;
		this.title = title;
	}

	public int getHome_team_uid() {
		return home_team_uid;
	}

	public void setHome_team_uid(int home_team_uid) {
		this.home_team_uid = home_team_uid;
	}

	public int getAway_team_uid() {
		return away_team_uid;
	}

	public void setAway_team_uid(int away_team_uid) {
		this.away_team_uid = away_team_uid;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getHead_uid() {
		return head_uid;
	}
	public void setHead_uid(int head_uid) {
		this.head_uid = head_uid;
	}
	public int getHome_matching_entry_uid() {
		return home_matching_entry_uid;
	}
	public void setHome_matching_entry_uid(int home_matching_entry_uid) {
		this.home_matching_entry_uid = home_matching_entry_uid;
	}
	public int getAway_matching_entry_uid() {
		return away_matching_entry_uid;
	}
	public void setAway_matching_entry_uid(int away_matching_entry_uid) {
		this.away_matching_entry_uid = away_matching_entry_uid;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getMatching_date() {
		return matching_date;
	}
	public void setMatching_date(String matching_date) {
		this.matching_date = matching_date;
	}
	public int getHome_score() {
		return home_score;
	}
	public void setHome_score(int home_score) {
		this.home_score = home_score;
	}
	public int getAway_score() {
		return away_score;
	}
	public void setAway_score(int away_score) {
		this.away_score = away_score;
	}
	public int getReady_num() {
		return ready_num;
	}
	public void setReady_num(int ready_num) {
		this.ready_num = ready_num;
	}
	public int getPlace_uid() {
		return place_uid;
	}
	public void setPlace_uid(int place_uid) {
		this.place_uid = place_uid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getHead_price() {
		return head_price;
	}
	public void setHead_price(int head_price) {
		this.head_price = head_price;
	}
	public String getDong_code() {
		return dong_code;
	}
	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "TMatchInfo [uid=" + uid + ", head_uid=" + head_uid + ", home_team_uid=" + home_team_uid
				+ ", away_team_uid=" + away_team_uid + ", home_matching_entry_uid="
				+ home_matching_entry_uid + ", away_matching_entry_uid=" + away_matching_entry_uid + ", create_date="
				+ create_date + ", matching_date=" + matching_date + ", home_score=" + home_score + ", away_score="
				+ away_score + ", ready_num=" + ready_num + ", place_uid=" + place_uid + ", price=" + price
				+ ", head_price=" + head_price + ", dong_code=" + dong_code + ", title=" + title + "]";
	}
}
