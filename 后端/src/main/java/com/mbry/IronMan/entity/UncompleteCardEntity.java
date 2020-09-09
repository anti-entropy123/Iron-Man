package com.mbry.IronMan.entity;

public class UncompleteCardEntity {
	
	private String userId;
	private String cardId;
	
	public UncompleteCardEntity() {}
	public UncompleteCardEntity(String userId, String cardId) {
		this.setUserId(userId);
		this.setCardId(cardId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}
