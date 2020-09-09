package com.mbry.IronMan.Dao;

import com.mbry.IronMan.BusinessObject.Comment.Comment;
import com.mbry.IronMan.BusinessObject.Comment.Reply;

public interface CommentDao {
    /**
     * 按页获取某帖子下主楼评论
     * @param cardId
     * @return 所有主楼评论(不含回复)
     */
    public Comment[] queryCommentsByCardId(String cardId, int page);
    /**
     * 获取某主楼评论下的所有回复
     * @param commentId
     * @return
     */
    public Reply[] queryRepliesByCommentId(String commentId);
    
    /**
     * 
     * @param comment
     * @return
     */
    public boolean createComment(Comment comment);

    /**
     * 删除某主楼评论, 同时删除其所有reply
     * @param commentId
     * @return
     */
    public boolean deleteComment(String commentId);
    
    /**
     * 仅删除某 repley
     * @param commentId
     * @return
     */
    public boolean deleteReply(String commentId);

}