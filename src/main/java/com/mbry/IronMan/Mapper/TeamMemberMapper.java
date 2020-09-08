package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeamMemberMapper {
	
	/**
	 * 插入一条映射
	 * @param teamId
	 * @param userId
	 */
	public void insertTeamMember(@Param("teamId")String teamId, @Param("userId")String userId);
	
	/**
	 * 动态删除映射
	 * @param teamId
	 * @param userId
	 */
	public void deleteTeamMember(@Param("teamId")String teamId, @Param("userId")String userId);
	
	/**
	 * 查询一个队伍里的userId
	 * @param teamId
	 * @return
	 */
	public String[] queryUserByTeamId(String teamId);
	
	/**
	 * 查询一个user所属的队伍
	 * @param userId
	 * @return
	 */
	public String queryTeamByUserId(String userId);

}
