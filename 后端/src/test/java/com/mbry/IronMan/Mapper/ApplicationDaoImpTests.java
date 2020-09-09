package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mbry.IronMan.BusinessObject.Application.CardApplication;
import com.mbry.IronMan.Dao.ApplicationDao;

@SpringBootTest
public class ApplicationDaoImpTests {
    
    @Autowired
    ApplicationDao ad;

    @Test
    public void test() {
        CardApplication ca = new CardApplication();
        ca.setApplicantId("12");
        ca.setCardId("12");
        ca.setDate("12");
        ca.setStatus(true);
        ca.setTargetId("12");
        System.out.println("123 " + ad.createApplication(ca));
    }

}