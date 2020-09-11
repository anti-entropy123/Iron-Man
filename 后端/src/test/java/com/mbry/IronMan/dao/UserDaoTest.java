package com.mbry.IronMan.dao;

import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    void queryUserByOpenId() {
        User user = userDao.queryUserByOpenId("1");
        System.out.println(user.getMobile());

    }

    @Test
    void queryCaptainByTeamId() {
        User user = userDao.queryCaptainByTeamId("236e1cfbf1c711ea9e601cbfc09c197a");
        System.out.println(user.getUserId());
    }
    
    @Test
    void queryUsersByTeamId() {
        User[] user = userDao.queryUsersByTeamId("32");
        System.out.println(user[0].getUserId());
    }

    @Test
    void queryUserByApplyId() {
        User user = userDao.queryUserByApplyId("2010912ff29f11eabb18cb0fe550e17a");
        System.out.println(user.getUserId());
    }

    @Test
    void queryUserByCardId() {
        User user = userDao.queryUserByCardId("0ad005f8f3f511ea80305dd7211e33a5");
        System.out.println(user.getUserId());
    }
    /**
     * 新增用户
     * @param user
     * @return 
     */
    @Test
    void registerUser() {
        User user = new User(
            "String userId",
            "String nickname",
            "String avatar",
            true,
            "String mobile",
            "String introduction"
        );
        userDao.registerUser(user);
    }
    
    /**
     * 
     * @param user
     * @return
     */
    @Test
    void updateUserDataByUserId() {
        User user = new User(
            "String userId",
            "String nickname",
            "String avatar",
            true,
            "new mobile",
            "String introduction"
        );
        userDao.updateUserDataByUserId(user);
    }

    @Test
    void bindMobileNumberByUserId() {
        userDao.bindMobileNumberByUserId("userId", "mobilePhone");
    }

    /**
     * 根据下述条件筛选
     * @param name 如果不需要则为 null
     * @param userId 如果不需要则为 null
     * @param mobileNumber 如果不需要则为 null
     * @return
     */
    @Test
    void queryUsers() {
        User[] users = userDao.queryUsers(1, null, null, null);
        for (User user: users) {
            System.out.println(user.getUserId());
        }
    }
    
}
