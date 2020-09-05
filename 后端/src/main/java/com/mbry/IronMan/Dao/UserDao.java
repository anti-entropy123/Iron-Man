package com.mbry.IronMan.Dao;

import com.mbry.IronMan.BusinessObject.User;

import org.springframework.stereotype.Service;

@Service
public interface UserDao {
    /**
     * 根据 openId 查用户数据
     * @param openId
     * @return 
     */
    public User queryUserByOpenId(String openId);

    /**
     * 根据teamId, 查询该队伍的队长
     * @param teamId
     * @return 
     */
    public User queryCaptainByTeamId(String teamId);
    /**
     * 根据teamId, 查询该队伍下所有成员
     * @param teamId
     * @return 成员列表, 要求队长位于数组之首
     */
    public User[] queryUsersByTeamId(String teamId);

    /**
     * 根据applicationId查询该申请的发起人
     * @param appliactionId
     * @return 
     */
    public User queryUserByApplyId(String appliactionId);
    /**
     * 根据cardId查帖子的发布者
     * @param cardId
     * @return
     */
    public User queryUserByCardId(String cardId);
    /**
     * 新增用户
     * @param user
     * @return 
     */
    public boolean registerUser(User user);
    
    /**
     * 
     * @param user
     * @return
     */
    public boolean updateUserDataByUserId(User user);

    /**
     * 为某用户绑定手机号
     * @param userId
     * @param mobilePhone
     * @return
     */
    public boolean bindMobileNumberByUserId(String userId, String mobilePhone);

    /**
     * 根据下述条件筛选
     * @param name 如果不需要则为 null
     * @param userId 如果不需要则为 null
     * @param mobileNumber 如果不需要则为 null
     * @return
     */
    public User[] queryUsers(int page, String name, String userId, String mobileNumber);
}