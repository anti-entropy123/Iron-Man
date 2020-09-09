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
            this.setCommentId(commentId);
            this.setCardId(cardId);
            this.setUserId(userId);
            this.setContent(content);
            this.setDate(date);
    }

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
}