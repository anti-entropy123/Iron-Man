package com.mbry.IronMan.entity;

public class ImageEntity {
	
	private String cardId;
	private String imageUrl;
	
	public ImageEntity() {}
	public ImageEntity(String cardId, String imageUrl) {
		this.setCardId(cardId);
		this.setImageUrl(imageUrl);
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
