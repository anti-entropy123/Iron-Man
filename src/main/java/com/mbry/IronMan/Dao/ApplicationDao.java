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
     * @return
     */
    public boolean createApplication(CardApplication app);

    /**
     * 
     * @param app
     * @return
     */
    public boolean createApplication(TeamApplication app);

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
    public Application[] queryApplicationByUserId(String targetUserId);
}