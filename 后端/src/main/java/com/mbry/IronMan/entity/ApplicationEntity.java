package com.mbry.IronMan.entity;

public class ApplicationEntity {
	
	private String applicationId;
	private String cardId;
	private String applicantId;
	private Boolean status;
	private String targetUserId;
	private String date;
	
	public ApplicationEntity() {}
	public ApplicationEntity(
			String applicationId,
			String cardId,
			String applicantId,
			Boolean status,
			String targetUserId,
			String date
		) {
		this.setApplicantId(applicantId);
		this.setCardId(cardId);
		this.setApplicationId(applicationId);
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
	
	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
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
