package com.mbry.IronMan.Service;

import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.ResponseBody.MessageResponseBody.GetAllMessageResponse;

import java.util.ArrayList;
import java.util.List;

import com.mbry.IronMan.BusinessObject.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    LogDao logDao;

    public GetAllMessageResponse.Data.Message[] getLogsByAimUser(String userId) {
        GetAllMessageResponse response = new GetAllMessageResponse();
        GetAllMessageResponse.Data data = response.new Data();
        List<GetAllMessageResponse.Data.Message> messages = new ArrayList<GetAllMessageResponse.Data.Message>();
        
        Log[] logs = logDao.queryLogByAimUserId(userId);
        for (Log log: logs) {
            GetAllMessageResponse.Data.Message message = data.new Message();
            message.setType(log.getType());
            message.setCardId(log.getCardId());
            message.setApplyId(log.getApplyId());
            message.setUserId(log.getUserId());
            message.setLogId(log.getLogId());
            message.setStatus(log.getStatus());
            messages.add(message);
            if(log.getType() > 1){
                logDao.setTrueByLogId(log.getLogId());
            }
        }

        return messages.toArray(new GetAllMessageResponse.Data.Message[messages.size()]);
    }

}