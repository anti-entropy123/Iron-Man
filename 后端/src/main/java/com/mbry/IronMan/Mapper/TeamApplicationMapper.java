package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.TeamApplicationEntity;

@Mapper
public interface TeamApplicationMapper {
	
	/**
	 * 插入一个组队申请
	 * @param teamApplicationEntity
	 */
	public void insertTeamApplication(TeamApplicationEntity teamApplicationEntity);
	
	/**
	 * 删除一个指定申请
	 * @param applicationId
	 */
	public void deleteTeamApplicationById(String applicationId);
	
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

	/**
	 * 根据申请id查询发起人
	 * @param applicationId
	 * @return
	 */
	public String queryApplicantIdById(String applicationId);
	
	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	public TeamApplicationEntity queryApplicationById(String applicationId);
	
	/**
	 * 将指定申请状态改为完成
	 * @param teamApplicationId
	 * @param status
	 */
	public void updateStatusByTeamApplicationId(@Param("applicationId")String applicationId, @Param("status")Boolean status);

}
