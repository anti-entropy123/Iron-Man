package com.mbry.IronMan.BusinessObject.Application;

public class TeamApplication extends Application{
	
    private String teamId;

    public TeamApplication(){}
    public TeamApplication(
        String applicationId,
        String applicantId,
        String targetUserId,
        Boolean status,
        String date,
        String teamId
    ){
        super(applicationId, applicantId, targetUserId, status, date);
        this.setTeamId(teamId);
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
	
}