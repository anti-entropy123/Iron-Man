package com.mbry.IronMan.RequestBody.PublishRequestBody;

import java.io.Serializable;

public class PublishRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6232384100949902996L;
    private String userId;
    private int type;
    private String title;
    private String introduce;
    private String location;
    private Double[] prices;
    private Double[] squares;
    private int unitType;
    private String[] images;
    private String require;
    private int unionNum;
    private Boolean hasHouseResource;
    private Double[] coordinates;

    public PublishRequest() {
    }

    public PublishRequest(
            String userId,
            int type,
            String title,
            String introduce,
            String location,
            Double[] prices,
            Double[] squares,
            int unitType,
            String[] images,
            String require,
            int unionNum,
            Boolean hasHouseResource,
            Double[] coordinates) {
        this.userId = userId;
        this.type = type;
        this.title =title;
        this.introduce = introduce;
        this.location = location;
        this.prices = prices;
        this.squares = squares;
        this.unitType = unitType;
        this.images = images;
        this.require = require;
        this.unionNum = unionNum;
        this.hasHouseResource = hasHouseResource;
        this.coordinates =coordinates;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double[] getPrices() {
        return this.prices;
    }

    public void setPrices(Double[] prices) {
        this.prices = prices;
    }

    public Double[] getSquares() {
        return this.squares;
    }

    public void setSquares(Double[] squares) {
        this.squares = squares;
    }

    public int getUnitType() {
        return this.unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String[] getImages() {
        return this.images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getRequire() {
        return this.require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public int getUnionNum() {
        return this.unionNum;
    }

    public void setUnionNum(int unionNum) {
        this.unionNum = unionNum;
    }

    public Boolean getHasHouseResource() {
        return this.hasHouseResource;
    }

    public void setHasHouseResource(Boolean hasHouseResource) {
        this.hasHouseResource = hasHouseResource;
    }

    public void setCoordinates(Double[] coordinates){
        this.coordinates =coordinates;
    }

    public Double[] getCoordinates(){
        return this.coordinates;
    }
    
}