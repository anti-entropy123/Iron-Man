package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mbry.IronMan.entity.CardEntity;

@SpringBootTest
class CardMapperTests {

	@Autowired
	CardMapper cardMapper;
	
	@Test
	void queryCardByCardId() {
		CardEntity ce = cardMapper.queryCardByCardId("1");
		System.out.println(ce.getUserId());
	}
	
	@Test
	void queryCardByCardIdAndStatus() {
		CardEntity[] ce = cardMapper.queryCardByUserIdAndStatus("1", true);
		System.out.println(ce.length);
		System.out.println(ce[0].getUserId());
	}
	
	@Test
	void queryCard() {
//		CardEntity[] ce = cardMapper.queryCards(0, 4, -1, "1",-1, -1, -1, -1, -1);
//		System.out.println(ce.length);
//		for(int i = 0; i < ce.length; i++) {
//			System.out.println(ce[i].getUserId());
//		}
	}
	
//	@Test
//	void insertCard() {
//		CardEntity ce = new CardEntity(
//				null,
//				"11",
//				10,
//				true,
//				"12222",
//				"12222",
//				"12222",
//				"12222",
//				12,
//				12,
//				12,
//				12,
//				12,
//				"11222",
//				11222,
//				false);
//		cardMapper.insertCard(ce);
//	}
	
	@Test
	void updateCard() {
		CardEntity ce = new CardEntity(
				"7",
				"11",
				10,
				true,
				"12222",
				"12222",
				"12222",
				"12222",
				12,
				12,
				12,
				12,
				12,
				"11222",
				11222,
				false);
		cardMapper.updateCard(ce);
	}
	
	@Test
	void finsihCard() {
		cardMapper.finsihCard("5");
	}
	
	@Test
	void queryUserIdById() {
		System.out.println(cardMapper.queryUserIdById("1"));
	}
}