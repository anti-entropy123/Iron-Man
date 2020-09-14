package com.mbry.IronMan.entity;

public class CardCoordinateEntity {

    private String cardId;
    private Double longitude; // 经度 
    private Double latitude;  // 纬度

    public CardCoordinateEntity() {}
    public CardCoordinateEntity(
        String cardId,
        Double longitude,
        Double latitude) {
        this.setCardId(cardId);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
}
