package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UncompleteCardMapper {

	/**
	 * 插入一条用户Card映射
	 * @param userId
	 * @param cardId
	 */
	public void insertUserAndCard(@Param("userId")String userId, @Param("cardId")String cardId);
	
	/**
	 * 查询符合userId的cardId
	 * @param userId
	 * @param cardId
	 */
	public String[] selectUserAndCard(String userId);
	
	/**
	 * 删除符合userId或cardId的映射
	 * @param userId
	 * @param cardId
	 */
	public void deleteUserAndCard(@Param("userId")String userId, @Param("cardId")String cardId);
}
