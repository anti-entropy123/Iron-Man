package com.mbry.IronMan.BusinessObject.Comment;

public class Reply extends Comment{
    private String replyToId;
    private String belongToId;
    
    Reply(){}

    Reply(
        String commentId,
        String cardId,
        String userId,
        String content,
        String date,
        String replyToId,
        String belongToId
        ){
            super(commentId, cardId, userId, content, date);
            this.replyToId = replyToId;
            this.belongToId = belongToId;
    }
}