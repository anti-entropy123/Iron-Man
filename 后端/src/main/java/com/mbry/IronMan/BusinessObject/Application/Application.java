package com.mbry.IronMan.BusinessObject.Application;

public class Application {
    private String applicationId;
    private String applicantId;
    private String targetId;
    private boolean status;
    private String date;

    public Application(){
        
    }

    public Application(
        String applicationId,
        String applicantId,
        String targetId,
        boolean status,
        String date
    ){
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.status = status;
        this.date = date;
    }
}