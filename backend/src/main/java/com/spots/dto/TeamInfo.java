package com.spots.dto;

public class TeamInfo {
	private int uid;
	private String team_name;
	private String team_intro;
	private String city_code;
	private double team_rate;
	private int team_win;
	private int team_lose;
	private int player_num;
	
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public int getPlayer_num() {
		return player_num;
	}
	public void setPlayer_num(int player_num) {
		this.player_num = player_num;
	}
	public double getTeam_rate() {
		return team_rate;
	}
	public void setTeam_rate(double team_rate) {
		this.team_rate = team_rate;
	}
	private int team_draw;
	private int captain_uid;
	
	public TeamInfo() {};
	public TeamInfo(String team_name, String team_intro, int captain_uid) {
		super();
		this.team_name = team_name;
		this.team_intro = team_intro;
		this.captain_uid = captain_uid;
	}
	public TeamInfo(int uid, String team_name, String team_intro, double team_rate, int team_win, int team_lose, int team_draw,
			int captain_uid) {
		super();
		this.uid = uid;
		this.team_name = team_name;
		this.team_intro = team_intro;
		this.team_rate = team_rate;
		this.team_win = team_win;
		this.team_lose = team_lose;
		this.team_draw = team_draw;
		this.captain_uid = captain_uid;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_intro() {
		return team_intro;
	}
	public void setTeam_intro(String team_intro) {
		this.team_intro = team_intro;
	}
	public int getTeam_win() {
		return team_win;
	}
	public void setTeam_win(int team_win) {
		this.team_win = team_win;
	}
	public int getTeam_lose() {
		return team_lose;
	}
	public void setTeam_lose(int team_lose) {
		this.team_lose = team_lose;
	}
	public int getTeam_draw() {
		return team_draw;
	}
	public void setTeam_draw(int team_draw) {
		this.team_draw = team_draw;
	}
	public int getCaptain_uid() {
		return captain_uid;
	}
	public void setCaptain_uid(int captain_uid) {
		this.captain_uid = captain_uid;
	}
	@Override
	public String toString() {
		return "TeamInfo [uid=" + uid + ", team_name=" + team_name + ", team_intro=" + team_intro + ", team_win="
				+ team_win + ", team_lose=" + team_lose + ", team_draw=" + team_draw + ", captain_uid=" + captain_uid
				+ "]";
	}
	
	
	
	
}
