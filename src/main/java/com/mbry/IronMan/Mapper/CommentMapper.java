package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.CommentEntity;

@Mapper
public interface CommentMapper {
	
	/**
	 * 插入一条评论
	 * @param commentEntity
	 */
	public void insertComment(CommentEntity commentEntity);
	
	/**
	 * 依据Id删除评论
	 * @param commentId
	 */
	public void deleteComment(
				@Param("commentId")String commentId, 
				@Param("belongToId")String belongToId
				);
	
	/**
	 * 返回指定页的指定帖子的评论
	 * @param cardId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public CommentEntity[] queryCommentByCardId(
				@Param("cardId")String cardId, 
				@Param("startIndex")int startIndex, 
				@Param("pageSize")int pageSize
				);

	/**
	 * 返回指定评论的回复
	 * @param commentId
	 * @return
	 */
	public CommentEntity[] queryRepliesByBelongId(String belongToId);
	
}
