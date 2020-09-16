package com.mbry.IronMan.BusinessObject.Card;

public class AskSellCard extends Card{
	
    private Double minPrice;
    private Double maxPrice;
    private Double minSquare;
    private Double maxSquare;

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
        Double minPrice,
        Double maxPrice,
        Double minSquare,
        Double maxSquare,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.setMinPrice(minPrice);
            this.setMaxPrice(maxPrice);
            this.setMinSquare(minSquare);
            this.setMaxSquare(maxSquare);
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
    
}