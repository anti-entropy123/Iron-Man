package com.mbry.IronMan.entity;

public class TeamEntity {
	
	private String teamId;
	private String captainId;
	private String cardId;
	private int maxNum;
	private String date;
	
	public TeamEntity() {}
	public TeamEntity(
				String teamId,
				String captainId,
				String cardId,
				int maxNum,
				String date) {
		this.setTeamId(teamId);
		this.setCaptainId(captainId);
		this.setCardId(cardId);
		this.setMaxNum(maxNum);
		this.setDate(date);
	}
	

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getCaptainId() {
		return captainId;
	}

	public void setCaptainId(String captainId) {
		this.captainId = captainId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
