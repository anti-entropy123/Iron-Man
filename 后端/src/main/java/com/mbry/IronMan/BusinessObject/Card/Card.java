package com.mbry.IronMan.BusinessObject.Card;

// 该类具有五个子类
// rent 出租
// sell 出售
// Ask for rent 求租
// Ask for sale 求售
// Find a roommate 求室友
public class Card {
    private String cardId;
    private String userId;
    private Boolean status;
    private String date;
    private String title;
    private String introduction;
    private String location;
    private int unitType;
    private String[] images;

    public Card(){}
    // super(cardId, userId, status, date, title, introduction, location, unitType);
    public Card(
        String cardId,
        String userId,
        Boolean status,
        String date,
        String title,
        String introduction,
        String location,
        int unitType,
        String[] images
        ){
            this.setCardId(cardId);
            this.setUserId(userId);
            this.setStatus(status);
            this.setDate(date);
            this.setTitle(title);
            this.setIntroduction(introduction);
            this.setLocation(location);
            this.setUnitType(unitType);
            this.setImages(images);
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
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
		if (userId == "") {
			userId = null;
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		if (title == "") {
			title = null;
		}
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
		if (introduction == "") {
			introduction = null;
		}
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
		if (location == "") {
			location = null;
		}
	}
	
	public int getUnitType() {
		return unitType;
	}
	
	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}
	
	public String[] getImages() {
		return images;
	}
	
	public void setImages(String[] images) {
		this.images = images;
	}
	
}