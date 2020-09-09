package com.mbry.IronMan.entity;

public class TeamMemberEntity {
	
	private String teamId;
	private String userId;
	
	public TeamMemberEntity() {}
	public TeamMemberEntity(String teamId, String userId) {
		this.setTeamId(teamId);
		this.setUserId(userId);
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
