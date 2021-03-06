package com.mbry.IronMan.BusinessObject;

public class Team {
    private String teamId;
    private String captainId;
    private String cardId;
    private int maxNum;
    private String date;
    private User[] members;

    public Team(){}
    public Team(
        String teamId,
        String captainId,
        String cardId,
        int maxNum,
        String date,
        User[] members
        ){
            this.setTeamId(teamId);
            this.setCaptainId(captainId);
            this.setCardId(cardId);
            this.setMaxNum(maxNum);
            this.setDate(date);
            this.setMembers(members);
    }
    
	public String getTeamId() {
		return teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
		if (teamId == "") {
			teamId = null;
		}
	}
	
	public String getCaptainId() {
		return captainId;
	}
	
	public void setCaptainId(String captainId) {
		this.captainId = captainId;
		if (captainId == "") {
			captainId = null;
		}
	}
	
	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
		if (cardId == "") {
			cardId = null;
		}
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
		if (date == "") {
			date = null;
		}
	}
	
	public User[] getMembers() {
		return members;
	}
	
	public void setMembers(User[] members) {
		this.members = members;
	}
	
	public Boolean isMaxMember(){
		return this.getMaxNum() <= this.members.length;
	}
}