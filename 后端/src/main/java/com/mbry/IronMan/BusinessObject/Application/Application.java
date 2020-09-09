package com.mbry.IronMan.BusinessObject.Application;

public class Application {
    private String applicationId;
    private String applicantId;
    private String targetId;
    private Boolean status;
    private String date;

    public Application(){
        
    }

    public Application(
        String applicationId,
        String applicantId,
        String targetId,
        Boolean status,
        String date
    ){
        this.setApplicationId(applicationId);
        this.setApplicantId(applicantId);
        this.setStatus(status);
        this.setDate(date);
    }

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
		if (applicationId == "") {
			applicationId = null;
		}
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
		if (applicantId == "") {
			applicantId = null;
		}
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
		if (targetId == "") {
			targetId = null;
		}
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
    
}