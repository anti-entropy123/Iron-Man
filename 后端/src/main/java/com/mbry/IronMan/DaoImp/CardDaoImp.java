package com.mbry.IronMan.DaoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Card.AskRentCard;
import com.mbry.IronMan.BusinessObject.Card.AskSellCard;
import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.BusinessObject.Card.RentCard;
import com.mbry.IronMan.BusinessObject.Card.SellCard;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.Mapper.CardMapper;

@Service
public class CardDaoImp implements CardDao {
	
	@Autowired
	CardMapper cardMapper;

	@Override
	public Card queryCardByCardId(String cardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card[] queryCardsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card[] queryFinishCardsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card[] queryALLCards(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentCard[] queryRentCards(int page, String location, Double[] priceRange, int unitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AskRentCard[] queryAskRentCards(int page, String location, Double[] priceRange, int unitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellCard[] querySellCards(int page, String location, Double[] priceRange, int unitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AskSellCard[] queryAskSellCards(int page, String location, Double[] priceRange, int unitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createCard(Card card) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCard(Card card) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishCard(String cardId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recordUncompleteCardForUser(String userId, String cardId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUncompleteCardForUser(String userId, String cardId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Card[] queryUncompleteCardsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
