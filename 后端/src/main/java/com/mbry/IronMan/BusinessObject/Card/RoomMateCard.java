package com.mbry.IronMan.BusinessObject.Card;

public class RoomMateCard extends Card{
    private String requirement;
    private int unionNum;
    private boolean hasHouseResource;
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;

    RoomMateCard(){}
    RoomMateCard(
        String cardId,
        String userId,
        boolean status,
        String date,
        String title,
        String introduction,
        String location,
        int unitType,
        String requirement,
        int unionNum,
        boolean hasHouseResource,
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
	}
	
	public int getUnionNum() {
		return unionNum;
	}
	
	public void setUnionNum(int unionNum) {
		this.unionNum = unionNum;
	}
	
	public boolean isHasHouseResource() {
		return hasHouseResource;
	}
	
	public void setHasHouseResource(boolean hasHouseResource) {
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