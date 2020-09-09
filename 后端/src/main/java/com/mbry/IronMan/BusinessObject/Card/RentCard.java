package com.mbry.IronMan.BusinessObject.Card;

public class RentCard extends Card{
    private String requirement; // 对租客要求
    private int unionNum;
    private double price;
    private double Square;

    public RentCard(){}
    public RentCard(
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
        double price,
        double square,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.setRequirement(requirement);
            this.setUnionNum(unionNum);
            this.setPrice(price);
            this.setSquare(square);
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
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getSquare() {
		return Square;
	}
	
	public void setSquare(double square) {
		Square = square;
	}
	
}