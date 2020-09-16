package com.mbry.IronMan.BusinessObject.Card;

public class AskRentCard extends Card{
    private int unionNum;
    private Double minPrice;
    private Double maxPrice;
    private Double minSquare;
    private Double maxSquare;
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
        Double minPrice,
        Double maxPrice,
        Double minSquare,
        Double maxSquare,
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
	
	public Double getMinPrice() {
		return minPrice;
	}
	
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	
	public Double getMaxPrice() {
		return maxPrice;
	}
	
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	public Double getMinSquare() {
		return minSquare;
	}
	
	public void setMinSquare(Double minSquare) {
		this.minSquare = minSquare;
	}
	
	public Double getMaxSquare() {
		return maxSquare;
	}
	
	public void setMaxSquare(Double maxSquare) {
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