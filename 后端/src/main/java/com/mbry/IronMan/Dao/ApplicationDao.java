package com.mbry.IronMan.Dao;

import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Application.Application;
import com.mbry.IronMan.BusinessObject.Application.CardApplication;
import com.mbry.IronMan.BusinessObject.Application.TeamApplication;

@Service
public interface ApplicationDao {
    /**
     * 
     * @param app
     * @return 返回新建的application的主键
     */
    public String createApplication(CardApplication app);

    /**
     * 
     * @param app
     * @return 返回新建的application的主键
     */
    public String createApplication(TeamApplication app);

    /**
     * 修改申请的状态为完成
     * @param app
     * @return
     */
    public boolean processApplication(TeamApplication app); 
    public boolean processApplication(CardApplication app); 

    /**
     * 查询发送给某用户的所有申请
     * @param userId
     * @return
     */
    public Application[] queryApplicationsByUserId(String targetUserId);

    /**
     * 根据 cardId 查询该card对应的有关的CardId
     * @param cardId 理论上 cardId 只能是租房的card的id
     * @return Application[] 中的对象的实际类型应该是CardApplication
     */
    public Application[] queryCardApplicationsByCardId(String cardId);

    /**
     * 根据 appId 查询对应的申请
     * @param ApplicationId
     * @return 应返回真实类型, 并向上转型
     */
    public Application queryApplicationByAppId(String applicationId);

    /**
     * 根据 UserId查询由该 User 发起的所有 CardApplication
     * @param applicantId 申请的发起者的Id
     * @return    CardApplication
     */
    public CardApplication[] queryCardAppByApplicantUserId(String applicantId);
    
}