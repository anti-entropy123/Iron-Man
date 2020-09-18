package com.mbry.IronMan.BusinessObject.Card;

public class SellCard extends Card{
    private Double price;
    private Double square;
    // private String requirement;
    private Double longitude; // 经度 
    private Double latitude;  // 纬度
    
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
        Double price,
        Double square,
        //String requirement,
        String[] images,
        Double longitude,
        Double latitude 
    ){
        super(cardId, userId, status, date, title, introduction, location, unitType, images);
        this.setPrice(price);
        this.setSquare(square);
        //this.setRequirement(requirement);
    }
    
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSquare() {
		return square;
	}
	
	public void setSquare(Double square) {
		this.square = square;
	}
	
	// public String getRequirement() {
	// 	return requirement;
	// }
	
	// public void setRequirement(String requirement) {
	// 	this.requirement = requirement;
	// }
    
    public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
    }
    
    public Double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
    }
}