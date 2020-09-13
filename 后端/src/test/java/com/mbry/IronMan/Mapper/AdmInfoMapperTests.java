package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdmInfoMapperTests {
    
    @Autowired
    AdmInfoMapper ad;

    @Test
    void test() {
        System.out.println(ad.queryAdm("234").getPassword());
    }
}
