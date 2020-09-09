package com.mbry.IronMan.BusinessObject.Card;

public class AskRentCard extends Card{
    private int unionNum;
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;
    private String requirement;

    public AskRentCard(){}
    public AskRentCard(
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
        double minPrice,
        double maxPrice,
        double minSquare,
        double maxSquare,
        String[] images
    ){
        super(cardId, userId, status, date, title, introduction, location, unitType, images);
        this.setUnionNum(unionNum);
        this.setMinPrice(minPrice);
        this.setMaxPrice(maxPrice);
        this.setMinSquare(minSquare);
        this.setMaxSquare(maxSquare);
        this.setRequirement(requirement);
    }
    
	public int getUnionNum() {
		return unionNum;
	}
	
	public void setUnionNum(int unionNum) {
		this.unionNum = unionNum;
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
	
	public String getRequirement() {
		return requirement;
	}
	
	public void setRequirement(String requirement) {
		this.requirement = requirement;
		if (requirement == "") {
			requirement = null;
		}
	}

}