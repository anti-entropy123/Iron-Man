package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	/**
	 * 插入一条user信息
	 * @param userEntity
	 */
	public void insertUser(UserEntity userEntity);
	
	/**
	 * 根据id查询user信息
	 * @param userId
	 * @return
	 */
	public UserEntity queryUserByIdForTeam(String userId);
	
	/**
	 * 根据id查询user信息
	 * @param userId
	 * @return
	 */
	public UserEntity queryUserById(String userId);
	
	/**
	 * 依据条件动态查询
	 * @param page
	 * @param name
	 * @param userId
	 * @param mobileNumber
	 * @return
	 */
	public UserEntity[] queryUsers(
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize,
			@Param("nickName")String nickName, 
			@Param("userId")String userId, 
			@Param("mobile")String mobile
			);
	
	/**
	 * 更新一条user信息
	 * @param userEntity
	 */
	public void updateUser(UserEntity userEntity);
	
	/**
	 * 更新指定user的手机号
	 * @param userId
	 * @param mobilePhone
	 */
	public void updateUserMobile(@Param("userId")String userId, @Param("mobile")String mobile);

}
