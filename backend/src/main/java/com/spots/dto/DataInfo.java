package com.spots.dto;

public class DataInfo {
	@Override
	public String toString() {
		return "DataInfo [uid=" + uid + ", goal=" + goal + ", assist=" + assist + ", blacklist=" + blacklist + ", mvp="
				+ mvp + "]";
	}
	private int uid;
	private int goal;
	private int assist;
	private int blacklist;
	private int mvp;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public int getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}
	public int getMvp() {
		return mvp;
	}
	public void setMvp(int mvp) {
		this.mvp = mvp;
	}
	
	
	
}
