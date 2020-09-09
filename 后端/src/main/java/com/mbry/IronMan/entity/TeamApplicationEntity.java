package com.mbry.IronMan.entity;

public class TeamApplicationEntity {
	
	private String applicationId;
	private String teamId;
	private String applicantId;
	private Boolean status;
	private String targetUserId;
	private String date;
	
	public TeamApplicationEntity() {}
	public TeamApplicationEntity(
			String applicationId,
			String teamId,
			String applicantId,
			Boolean status,
			String targetUserId,
			String date
		) {
		this.setApplicationId(applicationId);
		this.setTeamId(teamId);
		this.setApplicantId(applicantId);
		this.setStatus(status);
		this.setTargetUserId(targetUserId);
		this.setDate(date);
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
