package com.mbry.IronMan.Service;

import com.mbry.IronMan.Dao.ApplicationDao;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.ResponseBody.MessageResponseBody.GetAllMessageResponse;

import java.util.ArrayList;
import java.util.List;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.BusinessObject.Application.Application;
import com.mbry.IronMan.BusinessObject.Card.Card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private CardDao cardDao;

    private boolean isIgnoreThisMessage(Log log){
        // 筛选条件: 如果card归此用户所有且已经完成, 且此log对应的application的发起者并非实际完成的用户, 则不显示
        // 先检查此log是否关联了application
        if(log.getApplyId() == null){
            return false;
        }
        Application app = applicationDao.queryApplicationByAppId(log.getApplyId());
        Card card = cardDao.queryCardByCardId(log.getCardId());
        if(app==null || card == null){
            return true;
        }
        return card.isStatus() && app.isStatus()==false;
        // if (app.isStatus() && card.isStatus()) {
        //     return false;
        // } else {
        //     return true;
        // }
    }

    public GetAllMessageResponse.Data.Message[] getLogsByAimUser(String userId) {
        GetAllMessageResponse response = new GetAllMessageResponse();
        GetAllMessageResponse.Data data = response.new Data();
        List<GetAllMessageResponse.Data.Message> messages = new ArrayList<GetAllMessageResponse.Data.Message>();
        
        Log[] logs = logDao.queryLogByAimUserId(userId);
        for (Log log: logs) {
            if(isIgnoreThisMessage(log)){
                continue;
            }
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