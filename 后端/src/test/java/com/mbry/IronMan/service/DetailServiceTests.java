package com.mbry.IronMan.service;

import com.mbry.IronMan.Service.DetailService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DetailServiceTests {

    @Autowired
    DetailService d;
    
    @Test
    void test() {
        d.getApply("cardId");
    }
    
}
