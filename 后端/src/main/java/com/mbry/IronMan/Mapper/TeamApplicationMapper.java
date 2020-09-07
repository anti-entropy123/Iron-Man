package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.TeamApplicationEntity;

@Mapper
public interface TeamApplicationMapper {
	
	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	public TeamApplicationEntity queryApplicationById(String applicationId);
	
	/**
	 * 插入一个组队申请
	 * @param teamApplicationEntity
	 */
	public void insertTeamApplication(TeamApplicationEntity teamApplicationEntity);
	
	/**
	 * 将指定申请状态改为完成
	 * @param teamApplicationId
	 * @param status
	 */
	public void updateStatusByTeamApplicationId(@Param("applicationId")String applicationId, @Param("status")boolean status);
	
	/**
	 * 查询发送给指定用户的组队申请
	 * @param targetUserId
	 * @return
	 */
	public TeamApplicationEntity[] queryTeamApplicationsByTargetUserId(String targetUserId);

	/**
	 * 查询指定用户发送的组队申请
	 * @param applicantId
	 * @return
	 */
	public TeamApplicationEntity[] queryTeamApplicationsByApplicantId(String applicantId);

}
