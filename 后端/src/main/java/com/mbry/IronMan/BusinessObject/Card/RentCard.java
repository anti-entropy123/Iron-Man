package com.mbry.IronMan.BusinessObject.Card;

public class RentCard extends Card{
    private String requirement; // 对租客要求
    private int unionNum;
    private Double price;
    private Double square;
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
        Double price,
        Double square,
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