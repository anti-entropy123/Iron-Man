package com.mbry.IronMan.DaoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Comment.Comment;
import com.mbry.IronMan.BusinessObject.Comment.Reply;
import com.mbry.IronMan.Dao.CommentDao;
import com.mbry.IronMan.Mapper.CommentMapper;
import com.mbry.IronMan.entity.CommentEntity;
import com.mbry.IronMan.global.Global;

@Service
public class CommentDaoImp implements CommentDao {

	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public Comment[] queryCommentsByCardId(String cardId, int page) {
		int startIndex = (page - 1) * Global.pageSize;
		CommentEntity[] commentEntitys = commentMapper.queryCommentByCardId(cardId, startIndex, Global.pageSize);
		List<Comment> comments = new ArrayList<Comment>();
		for (int i = 0; i < commentEntitys.length; i++) {
			if (commentEntitys[i].getReplyToId() !=  null) {
				Reply reply = new Reply();
				this.setCommentBase(reply, commentEntitys[i]);
				reply.setReplyToId(commentEntitys[i].getReplyToId());
				reply.setBelongToId(commentEntitys[i].getBelongToId());
				comments.add(reply);
			} else {
				Comment comment = new Comment();
				this.setCommentBase(comment, commentEntitys[i]);
				comments.add(comment);
			}
		}
		return comments.toArray(new Comment[comments.size()]);
	}

	@Override
	public Reply[] queryRepliesByCommentId(String commentId) {
		CommentEntity[] commentEntitys = commentMapper.queryRepliesByBelongId(commentId);
		List<Reply> replies = new ArrayList<Reply>();
		for (int i = 0; i < commentEntitys.length; i++) {
			Reply reply = new Reply();
			this.setCommentBase(reply, commentEntitys[i]);
			reply.setReplyToId(commentEntitys[i].getReplyToId());
			reply.setBelongToId(commentEntitys[i].getBelongToId());
			replies.add(reply);
		}
		return replies.toArray(new Reply[replies.size()]);
	}

	@Override
	public boolean createComment(Comment comment) {
		try {
			CommentEntity commentEntity = new CommentEntity();
			commentEntity.setCommentId(comment.getCommentId());
			commentEntity.setCardId(comment.getCardId());
			commentEntity.setUserId(comment.getUserId());
			commentEntity.setContent(comment.getContent());
			commentEntity.setDate(comment.getDate());
			if (comment instanceof Reply) {
				commentEntity.setReplyToId(((Reply)comment).getReplyToId());
				commentEntity.setBelongToId(((Reply)comment).getBelongToId());
			}
			commentMapper.insertComment(commentEntity);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteComment(String commentId) {
		try {
			commentMapper.deleteComment(commentId, null);
			commentMapper.deleteComment(null, commentId);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteReply(String commentId) {
		try {
			commentMapper.deleteComment(commentId, null);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param comment
	 * @param commentEntity
	 */
	private void setCommentBase(Comment comment, CommentEntity commentEntity) {
		comment.setCommentId(commentEntity.getCommentId());
		comment.setCardId(commentEntity.getCardId());
		comment.setUserId(commentEntity.getUserId());
		comment.setContent(commentEntity.getContent());
		comment.setDate(commentEntity.getDate());
	}

}
