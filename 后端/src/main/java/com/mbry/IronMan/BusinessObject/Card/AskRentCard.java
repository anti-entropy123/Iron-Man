package com.mbry.IronMan.BusinessObject.Card;

public class AskRentCard extends Card{
    private int unionNum;
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;

    public AskRentCard(){}
    public AskRentCard(
        String cardId,
        String userId,
        boolean status,
        String date,
        String title,
        String introduction,
        String location,
        boolean unitType,
        String requirement,
        int unionNum,
        double minPrice,
        double maxPrice,
        double minSquare,
        double maxSquare,
        String[] images
    ){
        super(cardId, userId, status, date, title, introduction, location, unitType, images);
        this.unionNum = unionNum;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
    }
}