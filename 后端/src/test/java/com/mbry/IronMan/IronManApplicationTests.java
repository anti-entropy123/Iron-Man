package com.mbry.IronMan;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mbry.IronMan.Utils.ImageAPIUtil;

@SpringBootTest
class IronManApplicationTests {
	
	@Autowired
	ImageAPIUtil image;

	@Test
	void contextLoads()  {
        File file = new File("D:/WorkSpace/20200814161055_1.jpg");
        System.out.println(image.upload(file));
    }

}
