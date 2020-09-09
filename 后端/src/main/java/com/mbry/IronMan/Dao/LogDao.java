package com.mbry.IronMan.Dao;

import com.mbry.IronMan.BusinessObject.Log;

import org.springframework.stereotype.Service;

@Service
public interface LogDao {
    /**
     * 插入一条log
     * @param logEntity
     */
    public void addLog(Log log);

    /**
     * 查询log
     * @param aimUserId
     * @return
     */
    public Log[] queryLogByAimUserId(String aimUserId);

    /**
     * 将log置为已读
     * @param applyId
     */
    public void setTrueByApplyId(String applyId);
    
}
