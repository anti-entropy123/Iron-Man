package com.mbry.IronMan.BusinessObject;

public class Team {
    private String teamId;
    private String captainId;
    private String cardId;
    private int maxNum;
    private String date;
    private User[] members;

    public Team(){}
    public Team(
        String teamId,
        String captainId,
        String cardId,
        int maxNum,
        String date
        ){
            this.teamId = teamId;
            this.captainId = captainId;
            this.cardId = cardId;
            this.maxNum = maxNum;
            this.date = date;
    }

}