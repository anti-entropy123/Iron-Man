package com.mbry.IronMan.BusinessObject.Card;

public class AskSellCard extends Card{
	
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;

    public AskSellCard(){}
    public AskSellCard(
        String cardId,
        String userId,
        Boolean status,
        String date,
        String title,
        String introduction,
        String location,
        int unitType,
        double minPrice,
        double maxPrice,
        double minSquare,
        double maxSquare,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.setMinPrice(minPrice);
            this.setMaxPrice(maxPrice);
            this.setMinSquare(minSquare);
            this.setMaxSquare(maxSquare);
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