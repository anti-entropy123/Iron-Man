package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.ApplicationEntity;

@Mapper
public interface ApplicationMapper {
	
	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	public ApplicationEntity queryApplicationById(String applicationId);
	
	/**
	 * 插入一个申请
	 * @param applicationEntity
	 */
	public void insertApplication(ApplicationEntity applicationEntity);
	
	/**
	 * 将指定申请状态改为完成
	 * @param applicationId
	 * @param status
	 */
	public void updateStatusByApplicationId(@Param("applicationId")String applicationId, @Param("status")boolean status);
	
	/**
	 * 查询发送给指定用户的全部申请
	 * @param targetUserId
	 * @return
	 */
	public ApplicationEntity[] queryApplicationsByTargetUserId(String targetUserId);
	
	/**
	 * 查询指定用户发起的全部申请
	 * @param applicantId
	 * @return
	 */
	public ApplicationEntity[] queryApplicationsByApplicantId(String applicantId);
	
}
