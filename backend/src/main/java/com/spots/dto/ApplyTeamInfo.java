package com.spots.dto;

public class ApplyTeamInfo {
	private int team_uid;
	private int user_uid;
	private String comment;
	
	
	
	public ApplyTeamInfo(int team_uid, int user_uid, String comment) {
		super();
		this.team_uid = team_uid;
		this.user_uid = user_uid;
		this.comment = comment;
	}
	public int getTeam_uid() {
		return team_uid;
	}
	public void setTeam_uid(int team_uid) {
		this.team_uid = team_uid;
	}
	public int getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(int user_uid) {
		this.user_uid = user_uid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
}
