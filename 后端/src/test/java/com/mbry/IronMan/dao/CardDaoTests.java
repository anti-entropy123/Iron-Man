package com.mbry.IronMan.dao;

import com.mbry.IronMan.BusinessObject.Card.AskRentCard;
import com.mbry.IronMan.BusinessObject.Card.AskSellCard;
import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.BusinessObject.Card.RentCard;
import com.mbry.IronMan.BusinessObject.Card.RoomMateCard;
import com.mbry.IronMan.BusinessObject.Card.SellCard;
import com.mbry.IronMan.Dao.CardDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardDaoTests {

    @Autowired
    CardDao cardDao;

    @Test
    void queryCardByCardId() {
        cardDao.queryCardByCardId("4610d4aef12111ea9c951cbfc09c197a").getUserId();
    }

    /**
     * 查询某用户发起的所有帖子
     * @param userId
     * @return
     */
    @Test
    void queryCardsByUserId() {
        Card[] cards = cardDao.queryCardsByUserId("1");
        for (Card card: cards) {
            System.out.println(card.getCardId());
        }
    }

    @Test
    void queryFinishCardsByUserId() {
        Card[] cards = cardDao.queryFinishCardsByUserId("1");
        for (Card card: cards) {
            System.out.println(card.getCardId());
        }
    }

    @Test
    void queryALLCards() {
        Card[] cards = cardDao.queryALLCards(1);
        for (Card card: cards) {
            System.out.println(card.getCardId());
        }
    }

   @Test
    void queryRentCards() {
        RentCard[] cards = cardDao.queryRentCards(1, "1", null, null, -1);
        for (RentCard card: cards) {
            System.out.println(card.getCardId());
        }
    }
    
    @Test
    void queryAskRentCards() {
        AskRentCard[] cards = cardDao.queryAskRentCards(1, "1", null, null, -1);
        for (AskRentCard card: cards) {
            System.out.println(card.getCardId());
        }
    }
    /**
     * 
     * @param page
     * @param location
     * @param priceRange
     * @param unitType
     * @return
     */
    @Test
    void querySellCards() {
        SellCard[] cards = cardDao.querySellCards(1, "1", null, null, -1);
        for (SellCard card: cards) {
            System.out.println(card.getCardId());
        }
    }
   
   @Test
    void queryAskSellCards() {
        AskSellCard[] cards = cardDao.queryAskSellCards(1, "4", null, null, -1);
        for (AskSellCard card: cards) {
            System.out.println(card.getCardId());
        }
    }
    
    @Test
    void queryAskRoomMateCards(){
        RoomMateCard[] cards = cardDao.queryAskRoomMateCards(1, "1", null, null, -1, true);
        for (RoomMateCard card: cards) {
            System.out.println(card.getCardId());
        }
    }
    
    
    @Test
    void createCard() {
        String[] image = { "1", "2"};
        cardDao.createCard(new RoomMateCard(
            null,
            "userId",
            false,
        "String date",
        "String title",
        "String introduction",
        "String location",
        1,
        "String requirement",
        1,
        true,
        100.0,
        200.0,
        100.0,
        200.0,
        image
        ));
    }
    
    @Test
    void updateCard() {
        String[] image = { "n1", "n2"};
        cardDao.updateCard(new RoomMateCard(
            "0ad005f8f3f511ea80305dd7211e33a5",
            "userId",
            false,
        "nString date",
        "nString title",
        "nString introduction",
        "nString location",
        1,
        "nString requirement",
        1,
        true,
        100.0,
        200.0,
        100.0,
        200.0,
        image
        ));
    }

   @Test
    void finishCard() {
        cardDao.finishCard("0ad005f8f3f511ea80305dd7211e33a5");
    }

    @Test
    void recordUncompleteCardForUser() {
        cardDao.recordUncompleteCardForUser("userId", "0ad005f8f3f511ea80305dd7211e33a5");
    }

    @Test
    void deleteUncompleteCardForUser() {
        cardDao.deleteUncompleteCardForUser("userId", "cardId");
    }


    @Test
    void queryUncompleteCardsByUserId() {
        Card[] s = cardDao.queryUncompleteCardsByUserId("userId");
        for (Card card: s) {
            System.out.println(card.getDate());
        }
    }
    
}
