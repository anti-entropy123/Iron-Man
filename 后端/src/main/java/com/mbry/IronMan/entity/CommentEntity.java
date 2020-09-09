package com.mbry.IronMan.entity;

public class CommentEntity {
	
	private String commentId;
	private String cardId;
	private String userId;
	private String replyToId;
	private String belongToId;
	private String content;
	private String date;
	
	public CommentEntity() {}
	public CommentEntity(
				String commentId,
				String cardId,
				String userId,
				String replyToId,
				String belongToId,
				String content,
				String date
				) {
		this.setCommentId(commentId);
		this.setCardId(cardId);
		this.setUserId(userId);
		this.setReplyToId(replyToId);
		this.setBelongToId(belongToId);
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
