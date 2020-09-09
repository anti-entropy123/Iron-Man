package com.mbry.IronMan.DaoImp;

import java.util.ArrayList;
import java.util.List;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.Mapper.LogMapper;
import com.mbry.IronMan.entity.LogEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogDaoImp implements LogDao {

    @Autowired
    LogMapper logMapper;

    @Override
    public void addLog(Log log) {
        logMapper.insertLog(new LogEntity(log.getLogId(),
                                          log.getType(),
                                          log.getCardId(),
                                          log.getApplyId(),
                                          log.getUserId(),
                                          log.getAimUserId(),
                                          log.getStatus()));
    }

    @Override
    public Log[] queryLogByAimUserId(String aimUserId) {
        LogEntity[] logEntitys = logMapper.queryLogEntityByAimUserId(aimUserId);
        List<Log> logs = new ArrayList<Log>();
        for (LogEntity logEntity: logEntitys) {
            logs.add(new Log(logEntity.getLogId(),
                             logEntity.getType(),
                             logEntity.getCardId(),
                             logEntity.getApplyId(),
                             logEntity.getUserId(),
                             logEntity.getAimUserId(),
                            logEntity.getStatus()));
        }
        return logs.toArray(new Log[logs.size()]);
    }

    @Override
    public void setTrueByApplyId(String applyId) {
        logMapper.setTrueByApplyId(applyId);
    }
    
}
