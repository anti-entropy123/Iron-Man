package com.mbry.IronMan.Service;

import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.ResponseBody.MessageResponseBody.GetAllMessageResponse;

import java.util.ArrayList;
import java.util.List;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.BusinessObject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private UserDao userDao;

    public GetAllMessageResponse.Data.Message[] getLogsByAimUser(String userId) {
        GetAllMessageResponse response = new GetAllMessageResponse();
        GetAllMessageResponse.Data data = response.new Data();
        List<GetAllMessageResponse.Data.Message> messages = new ArrayList<GetAllMessageResponse.Data.Message>();
        
        Log[] logs = logDao.queryLogByAimUserId(userId);
        for (Log log: logs) {
            User fromUser = userDao.queryUserByOpenId(log.getUserId());     
            if(fromUser != null){
                GetAllMessageResponse.Data.Message message = data.new Message(
                    log.getType(),
                    log.getCardId(), 
                    log.getApplyId(), 
                    log.getUserId(), 
                    log.getLogId(),
                    log.getStatus(),
                    fromUser.getAvatar(),
                    fromUser.getNickname()
                );
                messages.add(message);
            }
            if(log.getType() > 1){
                logDao.setTrueByLogId(log.getLogId());
            }
        }
        return messages.toArray(new GetAllMessageResponse.Data.Message[messages.size()]);
    }

}