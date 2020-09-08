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
    private double[] prices;
    private double[] squares;
    private int unitType;
    private String[] images;
    private String require;
    private int unionNum;
    private boolean hasHouseResource;

    public PublishRequest() {
    }

    public PublishRequest(
            String userId,
            int type,
            String title,
            String introduce,
            String location,
            double[] prices,
            double[] squares,
            int unitType,
            String[] images,
            String require,
            int unionNum,
            boolean hasHouseResource) {
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

    public double[] getPrices() {
        return this.prices;
    }

    public void setPrices(double[] prices) {
        this.prices = prices;
    }

    public double[] getSquares() {
        return this.squares;
    }

    public void setSquares(double[] squares) {
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

    public boolean getHasHouseResource() {
        return this.hasHouseResource;
    }

    public void setHasHouseResource(boolean hasHouseResource) {
        this.hasHouseResource = hasHouseResource;
    }
}