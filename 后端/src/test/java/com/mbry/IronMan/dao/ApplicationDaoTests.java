package com.mbry.IronMan.dao;

import com.mbry.IronMan.BusinessObject.Application.Application;
import com.mbry.IronMan.BusinessObject.Application.CardApplication;
import com.mbry.IronMan.BusinessObject.Application.TeamApplication;
import com.mbry.IronMan.Dao.ApplicationDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationDaoTests {

    @Autowired
    ApplicationDao applicationDao;

    @Test
    void createApplication() {
        CardApplication app = new CardApplication(
            null,
            "applicantId",
            "targetUserId",
            false,
            "date",
            "cardId"
        );
        app.setTargetId("targetId");
        System.out.println(app.getTargetId());
        applicationDao.createApplication(app);
    }

    @Test
    void createTeamApplication() {
        TeamApplication app = new TeamApplication(
            null,
            "applicantId",
            "targetUserId",
            false,
            "date",
            "teamId"
        );
        app.setTargetId("targetId");
        System.out.println(app.getTargetId());
        applicationDao.createApplication(app);
    }
    /**
     * 修改申请的状态为完成
     * @param app
     * @return
     */
    @Test
    void processApplication() {
        CardApplication app = new CardApplication(
            "8f03b357f3df11ea80305dd7211e33a5",
            "applicantId",
            "targetUserId",
            false,
            "date",
            "cardId"
        );
        applicationDao.processApplication(app);
    }

    @Test
    void processTeamApplication() {
        TeamApplication app = new TeamApplication(
            "7f540484f3e211ea80305dd7211e33a5",
            "applicantId",
            "targetUserId",
            false,
            "date",
            "cardId"
        );
        applicationDao.processApplication(app);

    } 

    @Test
    void queryApplicationByUserId() {
        Application[] apps = applicationDao.queryApplicationsByUserId("12");
        for (Application app: apps) {
            System.out.println(app.getApplicationId());
        }
    }

    @Test
    void queryCardApplicationsByCardId() {
        Application[] apps = applicationDao.queryCardApplicationsByCardId("12");
        for (Application app: apps) {
            System.out.println(app.getApplicationId());
        }
    }

    @Test
    void queryApplicationByAppId() {
        Application app = applicationDao.queryApplicationByAppId("8f03b357f3df11ea80305dd7211e33a5");
        System.out.println(app.getTargetId());
    }

    @Test
    void queryCardAppByApplicantUserId() {
        CardApplication[] apps = applicationDao.queryCardAppByApplicantUserId("12");
        System.out.println(apps.length);
    }
    
}
