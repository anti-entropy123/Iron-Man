package com.mbry.IronMan.BusinessObject.Card;

public class RoomMateCard extends Card{
    private String requirement;
    private int unionNum;
    private boolean hasHouseResource;
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;

    RoomMateCard(){}
    RoomMateCard(
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
        boolean hasHouseResource,
        double minPrice,
        double maxPrice,
        double minSquare,
        double maxSquare,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.requirement = requirement;
            this.unionNum = unionNum;
            this.hasHouseResource = hasHouseResource;
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            this.minSquare = minSquare;
            this.maxSquare = maxSquare;
    }
}