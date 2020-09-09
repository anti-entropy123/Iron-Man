package com.mbry.IronMan.BusinessObject.Application;

public class TeamApplication extends Application{
    private String teamId;

    public TeamApplication(){}
    public TeamApplication(
        String applicationId,
        String applicantId,
        boolean status,
        String date,
        String teamId
    ){
        super(applicationId, applicantId, teamId, status, date);
        this.teamId = teamId;
    }
}