package com.mbry.IronMan.Dao;

import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Comment.Comment;
import com.mbry.IronMan.BusinessObject.Comment.Reply;

@Service
public interface CommentDao {
    /**
     * 按页获取某帖子下主楼评论
     * @param cardId
     * @return 所有主楼评论(不含回复)
     * 9.11 测试通过
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
    public Boolean createComment(Comment comment);

    /**
     * 删除某主楼评论, 同时删除其所有reply
     * @param commentId
     * @return
     */
    public Boolean deleteComment(String commentId);
    
    /**
     * 仅删除某 repley
     * @param commentId
     * @return
     */
    public Boolean deleteReply(String commentId);

    /**
     * 查询某个主楼评论的回复数量
     * @param commentId
     * @return
     */
    public int queryReplyNumberByCommentId(String commentId);

    /**
     * 
     * @param commentId
     * @return
     */
    public Comment queryCommentByCommentId(String commentId);

}