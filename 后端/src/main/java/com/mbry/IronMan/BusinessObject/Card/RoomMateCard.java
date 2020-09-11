package com.mbry.IronMan.BusinessObject.Card;

public class RoomMateCard extends Card{
    private String requirement;
    private int unionNum;
    private Boolean hasHouseResource;
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;

    public RoomMateCard(){}
    public RoomMateCard(
        String cardId,
        String userId,
        Boolean status,
        String date,
        String title,
        String introduction,
        String location,
        int unitType,
        String requirement,
        int unionNum,
        Boolean hasHouseResource,
        double minPrice,
        double maxPrice,
        double minSquare,
        double maxSquare,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.setRequirement(requirement);
            this.setUnionNum(unionNum);
            this.setHasHouseResource(hasHouseResource);
            this.setMinPrice(minPrice);
            this.setMaxPrice(maxPrice);
            this.setMinSquare(minSquare);
            this.setMaxSquare(maxSquare);
    }
    
	public String getRequirement() {
		return requirement;
	}
	
	public void setRequirement(String requirement) {
		this.requirement = requirement;
		if (requirement == "") {
            requirement = null;
        }
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
	
}