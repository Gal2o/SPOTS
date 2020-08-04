package com.spots.dto;

public class UserInfo {
	private int uid;
	private String email;
	private String password;
	private String birthday;
	private char skill;
	private String position_uid;
	private char ckMail;
	private int win;
	private int lose;
	private int draw;
	private int warning;
	private int team_uid;
	private String nickname;
	private String city_code;
	private String state_code;
	private String dong_code;
	private String comment;
	

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getDong_code() {
		return dong_code;
	}

	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserInfo() {}

	public UserInfo(String email, String password, String birthday, char skill, String position_uid, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.skill = skill;
		this.position_uid = position_uid;
		this.nickname = nickname;
	}

	

	public UserInfo(int uid, String email, String password, String birthday, char skill, String position_uid,
			char ckMail, int win, int lose, int draw, int warning, int team_uid, String nickname, String city_code,
			String state_code, String dong_code, String comment) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.skill = skill;
		this.position_uid = position_uid;
		this.ckMail = ckMail;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
		this.warning = warning;
		this.team_uid = team_uid;
		this.nickname = nickname;
		this.city_code = city_code;
		this.state_code = state_code;
		this.dong_code = dong_code;
		this.comment = comment;
	}

	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public char getSkill() {
		return skill;
	}

	public void setSkill(char skill) {
		this.skill = skill;
	}

	public String getPosition_uid() {
		return position_uid;
	}

	public void setPosition_uid(String position_uid) {
		this.position_uid = position_uid;
	}

	public char getCkMail() {
		return ckMail;
	}

	public void setCkMail(char ckMail) {
		this.ckMail = ckMail;
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

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", email=" + email + ", password=" + password + ", birthday=" + birthday
				+ ", skill=" + skill + ", position_uid=" + position_uid + ", ckMail=" + ckMail + ", win=" + win
				+ ", lose=" + lose + ", draw=" + draw + ", warning=" + warning + ", team_uid=" + team_uid
				+ ", nickname=" + nickname + ", city_code=" + city_code + ", state_code=" + state_code + ", dong_code="
				+ dong_code + ", comment=" + comment + "]";
	}


	
	
}