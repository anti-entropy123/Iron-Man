package com.mbry.IronMan.dao;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.Dao.LogDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogDaoTests {

    @Autowired
    LogDao logDao;

    @Test
    public void addLog() {
        logDao.addLog(
            
        new Log(
            12,
			0,
			"String cardId",
			"String applyId",
			"String userId",
			"String aimUserId",
			false
        )
        
        );
    }

    @Test
    void queryLogByAimUserId() {
        Log[] logs = logDao.queryLogByAimUserId("2");
        for (Log log: logs) {
            System.out.println(log.getLogId());
        }
    }

    /**
     * 将log置为已读
     * @param applyId
     */
    @Test
    void setTrueByApplyId() {
        logDao.setTrueByApplyId("2");
    }
    
}
