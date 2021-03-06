package com.mbry.IronMan.BusinessObject;

public class Log {

    private int logId;
    private int type;
    private String cardId;
    private String applyId;
    private String userId;
    private String aimUserId;
	private Boolean status;
	
	public Log() {}
	public Log(
			int logId,
			int type,
			String cardId,
			String applyId,
			String userId,
			String aimUserId,
			Boolean status
			) {
		this.setLogId(logId);
		this.setType(type);
		this.setCardId(cardId);
		this.setApplyId(applyId);
		this.setUserId(userId);
		this.setAimUserId(aimUserId);
		this.setStatus(status);
	}

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
		if (applyId == "") {
			applyId = null;
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		if (userId == "") {
			userId = null;
		}
	}

	public String getAimUserId() {
		return aimUserId;
	}

	public void setAimUserId(String aimUserId) {
		this.aimUserId = aimUserId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
    
}
