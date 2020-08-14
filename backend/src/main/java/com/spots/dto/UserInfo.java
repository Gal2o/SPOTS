package com.spots.dto;

public class UserInfo {
	private int uid;
	private String email;
	private String password;
	private int win;
	private int lose;
	private int draw;
	private int warning;
	private int team_uid;
	private String nickname;
	private String city_code;
	private String comment;
	private int mvp;
	private int blacklist;
	private int goal;
	private int assist;
	private int rate;
	private String admin;
	
	
	public UserInfo() {}
	
	public UserInfo(String email, String password, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
	
	public UserInfo(int uid, String email, String password, int win, int lose, int draw, int warning, int team_uid,
			String nickname, String city_code, String comment, int mvp, int blacklist, int goal, int assist, int rate,
			String admin) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
		this.warning = warning;
		this.team_uid = team_uid;
		this.nickname = nickname;
		this.city_code = city_code;
		this.comment = comment;
		this.mvp = mvp;
		this.blacklist = blacklist;
		this.goal = goal;
		this.assist = assist;
		this.rate = rate;
		this.admin = admin;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getWarning() {
		return warning;
	}
	public void setWarning(int warning) {
		this.warning = warning;
	}
	public int getTeam_uid() {
		return team_uid;
	}
	public void setTeam_uid(int team_uid) {
		this.team_uid = team_uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getMvp() {
		return mvp;
	}
	public void setMvp(int mvp) {
		this.mvp = mvp;
	}
	public int getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getAssist() {
		return assist;
	}
	public void setAssist(int assist) {
		this.assist = assist;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", email=" + email + ", password=" + password + ", win=" + win + ", lose="
				+ lose + ", draw=" + draw + ", warning=" + warning + ", team_uid=" + team_uid + ", nickname=" + nickname
				+ ", city_code=" + city_code + ", comment=" + comment + ", mvp=" + mvp + ", blacklist=" + blacklist
				+ ", goal=" + goal + ", assist=" + assist + ", rate=" + rate + "]";
	}
	
	
	
}