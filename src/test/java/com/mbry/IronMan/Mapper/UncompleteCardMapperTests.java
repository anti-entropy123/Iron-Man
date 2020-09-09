package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UncompleteCardMapperTests {

	@Autowired
	UncompleteCardMapper ucm;
	
	@Test
	void insertUserAndCard() {
		ucm.insertUserAndCard("123", "123");
		ucm.insertUserAndCard("123", "223");
		ucm.insertUserAndCard("123", "323");
	}
	
	@Test
	void selectUserAndCard() {
		String[] s = ucm.selectCardByUserId("123");
		System.out.println(s.length);
	}
	
	@Test
	void deleteUserAndCard() {
		ucm.deleteUserAndCard("123", null);
	}
}
