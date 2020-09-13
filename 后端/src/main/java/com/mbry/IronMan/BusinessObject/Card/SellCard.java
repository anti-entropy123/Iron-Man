package com.mbry.IronMan.BusinessObject.Card;

public class SellCard extends Card{
    private double price;
    private double square;
    private Double longitude; // 经度 
    private Double latitude;  // 纬度
    // private String requirement;
    
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
        //String requirement,
        String[] images,
        Double longitude, // 经度 
        Double latitude   // 纬度
    ){
        super(cardId, userId, status, date, title, introduction, location, unitType, images);
        this.setPrice(price);
        this.setSquare(square);
        //this.setRequirement(requirement);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
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
    
    public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
    }
    
    public double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	// public String getRequirement() {
	// 	return requirement;
	// }
	
	// public void setRequirement(String requirement) {
	// 	this.requirement = requirement;
	// }
    
}