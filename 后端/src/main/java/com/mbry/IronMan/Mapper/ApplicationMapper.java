package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.ApplicationEntity;

@Mapper
public interface ApplicationMapper {
	
	/**
	 * 插入一个申请
	 * @param applicationEntity
	 */
	public void insertApplication(ApplicationEntity applicationEntity);
	
	/**
	 * 删除一个指定申请
	 * @param applicationId
	 */
	public void deleteApplicationById(String applicationId);
	
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
	public ApplicationEntity queryApplicationById(String applicationId);

	/**
	 * 查询指向某cardId的全部申请
	 * @param cardId
	 * @return
	 */
	public ApplicationEntity[] queryApplicationByCardId(String cardId);

	public Integer queryApplicationByCardIdAndUserId(@Param("cardId")String cardId, @Param("applicantId")String userId );
	
	/**
	 * 将指定申请状态改为完成
	 * @param applicationId
	 * @param status
	 */
	public void updateStatusByApplicationId(@Param("applicationId")String applicationId, @Param("status")Boolean status);
	
}
