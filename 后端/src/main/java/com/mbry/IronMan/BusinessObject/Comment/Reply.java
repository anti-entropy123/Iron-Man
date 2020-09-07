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
            this.setReplyToId(replyToId);
            this.setBelongToId(belongToId);
    }

	public String getReplyToId() {
		return replyToId;
	}

	public void setReplyToId(String replyToId) {
		this.replyToId = replyToId;
	}

	public String getBelongToId() {
		return belongToId;
	}

	public void setBelongToId(String belongToId) {
		this.belongToId = belongToId;
	}
    
}