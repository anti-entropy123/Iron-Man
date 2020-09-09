package com.mbry.IronMan.Mapper;

import com.mbry.IronMan.entity.LogEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    
    /**
     * 插入一条log
     * @param logEntity
     */
    public void insertLog(LogEntity logEntity);

    /**
     * 根据logId删除一条log
     * @param LogId
     */
    public void deleteLog(String LogId);

    /**
     * 根据applyId删除一条log
     * @param applyId
     */
    public void deleteLogByApplyId(String applyId);

    /**
     * 查询log
     * @param aimUserId
     * @return
     */
    public LogEntity[] queryLogEntityByAimUserId(String aimUserId);

    /**
     * 将log置为已读
     * @param applyId
     */
    public void setTrueByApplyId(String applyId);

}
