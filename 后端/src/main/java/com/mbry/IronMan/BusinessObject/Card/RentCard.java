package com.mbry.IronMan.BusinessObject.Card;

public class RentCard extends Card{
    private String requirement; // 对租客要求
    private int unionNum;
    private double price;
    private double square;
    private Double longitude; // 经度 
    private Double latitude;  // 纬度

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
        String[] images,
        Double longitude, // 经度 
        Double latitude   // 纬度
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.setRequirement(requirement);
            this.setUnionNum(unionNum);
            this.setPrice(price);
            this.setSquare(square);
            this.setLongitude(longitude);
            this.setLatitude(latitude);
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
}