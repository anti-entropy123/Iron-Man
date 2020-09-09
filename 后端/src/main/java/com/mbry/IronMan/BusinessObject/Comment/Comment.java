package com.mbry.IronMan.BusinessObject.Comment;

public class Comment {
    private String commentId;
    private String cardId;
    private String userId;
    private String content;
    private String date;

    public Comment(){
    }

    public Comment(
        String commentId,
        String cardId,
        String userId,
        String content,
        String date
        ){
            this.commentId = commentId;
            this.cardId = cardId;
            this.userId = userId;
            this.content = content;
            this.date = date;
    }
}