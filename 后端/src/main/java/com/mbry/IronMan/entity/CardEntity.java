package com.mbry.IronMan.entity;

public class CardEntity {
	
	private String cardId;
	private String userId;
	private int type = -1;
	private Boolean status;
	private String date;
	private String title;
	private String introduction;
	private String location;
	private double minPrice = -1;
	private double maxPrice = -1;
	private double minSquare = -1;
	private double maxSquare = -1;
	private int unitType = -1;
	private String requirement;
	private int unionNum = 0;
	private Boolean hasHouseResource;
	
	public CardEntity() {}
	public CardEntity(
			String cardId,
			String userId,
			int type,
			Boolean status,
			String date,
			String title,
			String introduction,
			String location,
			double minPrice,
			double maxPrice,
			double minSquare,
			double maxSquare,
			int unitType,
			String requirement,
			int unionNum,
			Boolean hasHouseResource
		) {
		this.setCardId(cardId);
		this.setUserId(userId);
		this.setType(type);
		this.setStatus(status);
		this.setDate(date);
		this.setTitle(title);
		this.setIntroduction(introduction);
		this.setLocation(location);
		this.setMinPrice(minPrice);
		this.setMaxPrice(maxPrice);
		this.setMinSquare(minSquare);
		this.setMaxSquare(maxSquare);
		this.setUnitType(unitType);
		this.setRequirement(requirement);
		this.setUnionNum(unionNum);
		this.setHasHouseResource(hasHouseResource);
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinSquare() {
		return minSquare;
	}

	public void setMinSquare(double minSquare) {
		this.minSquare = minSquare;
	}

	public double getMaxSquare() {
		return maxSquare;
	}

	public void setMaxSquare(double maxSquare) {
		this.maxSquare = maxSquare;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public int getUnionNum() {
		return unionNum;
	}

	public void setUnionNum(int unionNum) {
		this.unionNum = unionNum;
	}

	public Boolean isHasHouseResource() {
		return hasHouseResource;
	}

	public void setHasHouseResource(Boolean hasHouseResource) {
		this.hasHouseResource = hasHouseResource;
	}

}
