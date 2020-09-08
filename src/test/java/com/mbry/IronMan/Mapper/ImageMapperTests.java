package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageMapperTests {
	
	@Autowired
	ImageMapper imageMapper;

//	@Test
//	void insertImage() {
//		imageMapper.insertImage("123", "123");
//		imageMapper.insertImage("123", "223");
//		imageMapper.insertImage("123", "323");
//		imageMapper.insertImage("123", "423");
//	}
	
//	@Test
//	void queryImageByCardId() {
//		String[] s = imageMapper.queryImageByCardId("123");
//		System.out.println(s.length);
//	}
//	
//	@Test
//	void queryFirstImageByCardId() {
//		System.out.println(imageMapper.queryFirstImageByCardId("123"));
//	}
//	
	@Test
	void deleteImageByCardId() {
		imageMapper.deleteImageByCardId("123");
	}
}
