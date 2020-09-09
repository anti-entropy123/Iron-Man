package com.mbry.IronMan.BusinessObject.Card;

public class SellCard extends Card{
    private double price;
    private double square;
    private String requirement;
    
    public SellCard(){}
    public SellCard(
        String cardId,
        String userId,
        Boolean status,
        String date,
        String title,
        String introduction,
        String location,
        int unitType,
        double price,
        double square,
        String requirement,
        String[] images
    ){
        super(cardId, userId, status, date, title, introduction, location, unitType, images);
        this.setPrice(price);
        this.setSquare(square);
        this.setRequirement(requirement);
    }
    
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getSquare() {
		return square;
	}
	
	public void setSquare(double square) {
		this.square = square;
	}
	
	public String getRequirement() {
		return requirement;
	}
	
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
    
}