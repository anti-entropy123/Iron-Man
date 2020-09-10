package com.mbry.IronMan.Service;

import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.RequestBody.PersonRequestBody.UpdateInfoRequest;
import com.mbry.IronMan.ResponseBody.PersonResponseBody.GetInfoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserDao userDao;

    /**
     * 
     * @param userId
     * @return
     */
    public GetInfoResponse getUserInfo(String userId) {
        try {
            User user = userDao.queryUserByOpenId(userId);
            GetInfoResponse getInfoResponse = new GetInfoResponse(
                user.getNickname(),
                user.getSex(),
                user.getMobile(),
                user.getIntroduction(),
                user.getAvatar()
            );
            getInfoResponse.setResult(1);
            getInfoResponse.setMessage("mbrynb");
            return getInfoResponse;
        } catch (Exception e) {
            e.printStackTrace();
            GetInfoResponse getInfoResponse = new GetInfoResponse();
            getInfoResponse.setResult(0);
            getInfoResponse.setMessage("server error");
            return getInfoResponse;
        }
    }

    public boolean updateUserInfo(UpdateInfoRequest updateInfoRequest) {
        User user = new User(
            updateInfoRequest.getUserId(),
            updateInfoRequest.getName(),
            updateInfoRequest.getAvatarUrl(),
            updateInfoRequest.getSex(),
            null,
            updateInfoRequest.getIntroduce()
        );
        return userDao.updateUserDataByUserId(user);
    }

}
