package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.TeamEntity;

@Mapper
public interface TeamMapper {
	
	/**
	 * 插入一条数据
	 * @param teamEntity
	 */
	public void insertTeam(TeamEntity teamEntity);
	
	/**
	 * 根据动态条件删除team
	 * @param teamId
	 * @param cardId
	 */
	public void deleteTeam(@Param("teamId")String teamId, @Param("cardId")String cardId);
	
	/**
	 * 根据cardId返回Team
	 * @param cardId
	 * @return
	 */
	public TeamEntity[] queryTeamsByCardId(String cardId);
	
	/**
	 * 根据teamId返回Team
	 * @param teamId
	 * @return
	 */
	public TeamEntity queryTeamByTeamId(String teamId);
	
	/**
	 * 查询某队队长id
	 * @param teamId
	 * @return
	 */
	public String queryCaptainIdByTeamId(String teamId);

	/**
	 * 返回指定card下指定队长的队伍
	 * @param captainId
	 * @param cardId
	 * @return
	 */
	public TeamEntity queryTeamByCapAndCard(@Param("captainId")String captainId, @Param("cardId")String cardId);
	
}
