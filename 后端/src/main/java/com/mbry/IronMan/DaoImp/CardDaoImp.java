package com.mbry.IronMan.DaoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Card.AskRentCard;
import com.mbry.IronMan.BusinessObject.Card.AskSellCard;
import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.BusinessObject.Card.RentCard;
import com.mbry.IronMan.BusinessObject.Card.RoomMateCard;
import com.mbry.IronMan.BusinessObject.Card.SellCard;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.Mapper.CardMapper;
import com.mbry.IronMan.Mapper.ImageMapper;
import com.mbry.IronMan.Mapper.UncompleteCardMapper;
import com.mbry.IronMan.entity.CardEntity;
import com.mbry.IronMan.global.Global;


@Service
public class CardDaoImp implements CardDao {
	
	@Autowired
	CardMapper cardMapper;
	@Autowired
	ImageMapper imageMapper;
	@Autowired
	UncompleteCardMapper uncompleteCardMapper;

	@Override
	public Card queryCardByCardId(String cardId) {
		CardEntity cardEntity = cardMapper.queryCardByCardId(cardId);
		return this.getCardBOByEntity(cardEntity);
	}

	@Override
	public Card[] queryCardsByUserId(String userId) {
		CardEntity[] cardEntitys = cardMapper.queryCardByUserIdAndStatus(userId, null);
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getCardBOByEntity(cardEntitys[i]));
		}
		return cards.toArray(new Card[cards.size()]);
	}

	@Override
	public Card[] queryFinishCardsByUserId(String userId) {
		CardEntity[] cardEntitys = cardMapper.queryCardByUserIdAndStatus(userId, true);
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getCardBOByEntity(cardEntitys[i]));
		}
		return cards.toArray(new Card[cards.size()]);
	}

	@Override
	public Card[] queryALLCards(int page) {
		int startIndex = (page - 1) * Global.pageSize;
		CardEntity[] cardEntitys = cardMapper.queryCards(startIndex, Global.pageSize, 0, null, 0, 0, 0, 0, -1, null);
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getCardBOByEntity(cardEntitys[i]));
		}
		return cards.toArray(new Card[cards.size()]);
	}

	@Override
	public RentCard[] queryRentCards(int page, String location, Double[] priceRange, int unitType) {
		int startIndex = (page - 1) * Global.pageSize;
		CardEntity[] cardEntitys = cardMapper.queryCards(
				startIndex, 
				Global.pageSize, 
				Global.rentType, 
				location, 
				priceRange[0], 
				priceRange[1], 
				0, 
				0, 
				unitType,
				null);
		List<RentCard> cards = new ArrayList<RentCard>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getRentCard(cardEntitys[i]));
		}
		return cards.toArray(new RentCard[cards.size()]);
	}

	@Override
	public AskRentCard[] queryAskRentCards(int page, String location, Double[] priceRange, int unitType) {
		int startIndex = (page - 1) * Global.pageSize;
		CardEntity[] cardEntitys = cardMapper.queryCards(
				startIndex, 
				Global.pageSize, 
				Global.askRentType, 
				location, 
				priceRange[0], 
				priceRange[1], 
				0, 
				0, 
				unitType,
				null);
		List<AskRentCard> cards = new ArrayList<AskRentCard>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getAskRentCard(cardEntitys[i]));
		}
		return cards.toArray(new AskRentCard[cards.size()]);
	}

	@Override
	public SellCard[] querySellCards(int page, String location, Double[] priceRange, int unitType) {
		int startIndex = (page - 1) * Global.pageSize;
		CardEntity[] cardEntitys = cardMapper.queryCards(
				startIndex, 
				Global.pageSize, 
				Global.sellType, 
				location, 
				priceRange[0], 
				priceRange[1], 
				0, 
				0, 
				unitType,
				null);
		List<SellCard> cards = new ArrayList<SellCard>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getSellCard(cardEntitys[i]));
		}
		return cards.toArray(new SellCard[cards.size()]);
	}

	@Override
	public AskSellCard[] queryAskSellCards(int page, String location, Double[] priceRange, int unitType) {
		int startIndex = (page - 1) * Global.pageSize;
		CardEntity[] cardEntitys = cardMapper.queryCards(
				startIndex, 
				Global.pageSize, 
				Global.sellType, 
				location, 
				priceRange[0], 
				priceRange[1], 
				0, 
				0, 
				unitType,
				null);
		List<AskSellCard> cards = new ArrayList<AskSellCard>();
		for (int i = 0; i < cardEntitys.length; i++) {
			cards.add(this.getAskSellCard(cardEntitys[i]));
		}
		return cards.toArray(new AskSellCard[cards.size()]);
	}

	@Override
	public boolean createCard(Card card) {
		try {
			cardMapper.insertCard(this.getCardEntityFromBO(card));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCard(Card card) {
		try {
			cardMapper.updateCard(this.getCardEntityFromBO(card));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean finishCard(String cardId) {
		try {	
			cardMapper.finsihCard(cardId);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean recordUncompleteCardForUser(String userId, String cardId) {
		try {
			uncompleteCardMapper.insertUserAndCard(userId, cardId);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUncompleteCardForUser(String userId, String cardId) {
		try {
			uncompleteCardMapper.deleteUserAndCard(userId, cardId);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Card[] queryUncompleteCardsByUserId(String userId) {
		String[] cardIds = uncompleteCardMapper.selectCardByUserId(userId);
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < cardIds.length; i++) {
			CardEntity cardEntity = cardMapper.queryCardByCardId(cardIds[i]);
			cards.add(this.getCardBOByEntity(cardEntity));
		}
		return cards.toArray(new Card[cards.size()]);
	}

	/**
	 * 将cardEntity按照type转化为五种Card之一
	 * @param cardEntity
	 * @return
	 */
	private Card getCardBOByEntity(CardEntity cardEntity) {
		
		int type = cardEntity.getType();
		/**
		 * type = 1 出租
		 */
		if (type == 1) {
			return this.getRentCard(cardEntity);
		} 
		/**
		 * type = 2 出售
		 */
		else if (type == 2) {
			return this.getSellCard(cardEntity);
		} 
		/**
		 * type = 3 求租
		 */
		else if (type == 3) {
			return this.getAskRentCard(cardEntity);
		} 
		/**
		 * type = 4 求售
		 */
		else if (type == 4) {
			return this.getAskSellCard(cardEntity);
		} 
		/**
		 * type = 5 求室友
		 */
		else if (type == 5) {
			return this.getRoomMateCard(cardEntity);
		} else {
			return null;
		}
	}
	
	/**
	 * type = 1
	 * 出租
	 * @param cardEntity
	 * @param card
	 */
	private RentCard getRentCard(CardEntity cardEntity) {
		RentCard card = new RentCard();
		this.setCardBase(cardEntity, card);
		card.setRequirement(cardEntity.getRequirement());
		card.setUnionNum(cardEntity.getUnionNum());
		card.setPrice(cardEntity.getMinPrice());
		card.setSquare(cardEntity.getMinSquare());
		return card;
	}
	
	/**
	 * type = 2
	 * 出售
	 * @param cardEntity
	 * @param card
	 */
	private SellCard getSellCard(CardEntity cardEntity) {
		SellCard card = new SellCard();
		this.setCardBase(cardEntity, card);
		//card.setRequirement(cardEntity.getRequirement());
		card.setPrice(cardEntity.getMinPrice());
		card.setSquare(cardEntity.getMinSquare());
		return card;
	}
	
	/**
	 * type = 3 
	 * 求租
	 * @param cardEntity
	 * @param card
	 */
	private AskRentCard getAskRentCard(CardEntity cardEntity) {
		AskRentCard card = new AskRentCard();
		this.setCardBase(cardEntity, card);
		card.setRequirement(cardEntity.getRequirement());
		card.setUnionNum(cardEntity.getUnionNum());
		card.setMinPrice(cardEntity.getMinPrice());
		card.setMaxPrice(cardEntity.getMaxPrice());
		card.setMinSquare(cardEntity.getMinSquare());
		card.setMaxSquare(cardEntity.getMaxSquare());
		return card;
	}
	
	/**
	 * type = 4
	 * 求售
	 * @param cardEntity
	 * @param card
	 */
	private AskSellCard getAskSellCard(CardEntity cardEntity) {
		AskSellCard card = new AskSellCard();
		this.setCardBase(cardEntity, card);
		card.setMinPrice(cardEntity.getMinPrice());
		card.setMaxPrice(cardEntity.getMaxPrice());
		card.setMinSquare(cardEntity.getMinSquare());
		card.setMaxSquare(cardEntity.getMaxSquare());
		return card;
	}
	
	/**
	 * type = 5
	 * 求室友
	 * @param cardEntity
	 * @param card
	 */
	private RoomMateCard getRoomMateCard(CardEntity cardEntity) {
		RoomMateCard card = new RoomMateCard();
		this.setCardBase(cardEntity, card);
		card.setMinPrice(cardEntity.getMinPrice());
		card.setMaxPrice(cardEntity.getMaxPrice());
		card.setMinSquare(cardEntity.getMinSquare());
		card.setMaxSquare(cardEntity.getMaxSquare());
		card.setRequirement(cardEntity.getRequirement());
		card.setHasHouseResource(cardEntity.isHasHouseResource());
		card.setUnionNum(cardEntity.getUnionNum());
		return card;
	}
	
	/**
	 * 
	 * @param cardEntity
	 * @param card
	 */
	private void setCardBase(CardEntity cardEntity, Card card) {
		card.setCardId(cardEntity.getCardId());
		card.setUserId(cardEntity.getUserId());
		card.setStatus(cardEntity.isStatus());
		card.setDate(cardEntity.getDate());
		card.setTitle(cardEntity.getTitle());
		card.setIntroduction(cardEntity.getIntroduction());
		card.setLocation(cardEntity.getLocation());
		card.setUnitType(cardEntity.getUnitType());
		card.setImages(imageMapper.queryImageByCardId(cardEntity.getCardId()));
	}
	
	/**
	 * 将五种Card转为CardEntity
	 * @param card
	 * @return
	 */
	private CardEntity getCardEntityFromBO(Card card) {
		/*
		 * type = 1 出租
		 */
		if (card instanceof RentCard) {
			return this.getCardEntityFromRent((RentCard)card);
		}
		/*
		 * type = 2 出售
		 */
		else if (card instanceof SellCard) {
			return this.getCardEntityFromSell((SellCard)card);
		}
		/*
		 * type = 3 求租
		 */
		else if (card instanceof AskRentCard) {
			return this.getCardEntityFromAR((AskRentCard)card);
		}
		/*
		 * type = 4 求售
		 */
		else if (card instanceof AskSellCard) {
			return this.getCardEntityFromAS((AskSellCard)card);
		}
		/*
		 * type = 5 求室友
		 */
		else if (card instanceof RoomMateCard) {
			return this.getCardEntityFromRM((RoomMateCard)card);
		} else {
			return null;
		}
	}
	
	/**
	 * type = 1
	 * 出租
	 * @param Card
	 * @return
	 */
	private CardEntity getCardEntityFromRent(RentCard card) {
		CardEntity cardEntity = new CardEntity();
		this.setCardEntityBase(cardEntity, card);
		cardEntity.setRequirement(card.getRequirement());
		cardEntity.setUnionNum(card.getUnionNum());
		cardEntity.setMinPrice(card.getPrice());
		cardEntity.setMinSquare(card.getSquare());
		return cardEntity;
	}
	
	/**
	 * type = 2
	 * 出售
	 * @param card
	 * @return
	 */
	private CardEntity getCardEntityFromSell(SellCard card) {
		CardEntity cardEntity = new CardEntity();
		this.setCardEntityBase(cardEntity, card);
		//cardEntity.setRequirement(card.getRequirement());
		cardEntity.setMinPrice(card.getPrice());
		cardEntity.setMaxSquare(card.getSquare());
		return cardEntity;
	}
	
	/**
	 * type = 3
	 * 求租
	 * @param card
	 * @return
	 */
	private CardEntity getCardEntityFromAR(AskRentCard card) {
		CardEntity cardEntity = new CardEntity();
		this.setCardEntityBase(cardEntity, card);
		cardEntity.setRequirement(card.getRequirement());
		cardEntity.setUnionNum(card.getUnionNum());
		cardEntity.setMinPrice(card.getMinPrice());
		cardEntity.setMaxPrice(card.getMaxPrice());
		cardEntity.setMinSquare(card.getMinSquare());
		cardEntity.setMaxSquare(card.getMaxSquare());
		return cardEntity;
	}
	
	/**
	 * type = 4
	 * 求售
	 * @param card
	 * @return
	 */
	private CardEntity getCardEntityFromAS(AskSellCard card) {
		CardEntity cardEntity = new CardEntity();
		this.setCardEntityBase(cardEntity, card);
		cardEntity.setMinPrice(card.getMinPrice());
		cardEntity.setMaxPrice(card.getMaxPrice());
		cardEntity.setMinSquare(card.getMinSquare());
		cardEntity.setMaxSquare(card.getMaxSquare());
		return cardEntity;
	}
	
	/**
	 * type = 5
	 * @param card
	 * @return
	 */
	private CardEntity getCardEntityFromRM(RoomMateCard card) {
		CardEntity cardEntity = new CardEntity();
		this.setCardEntityBase(cardEntity, card);
		cardEntity.setMinPrice(card.getMinPrice());
		cardEntity.setMaxPrice(card.getMaxPrice());
		cardEntity.setMinSquare(card.getMinSquare());
		cardEntity.setMaxSquare(card.getMaxSquare());
		cardEntity.setRequirement(card.getRequirement());
		cardEntity.setHasHouseResource(card.isHasHouseResource());
		cardEntity.setUnionNum(card.getUnionNum());
		return cardEntity;
	}
	
	/**
	 * @param cardEntity
	 * @param card
	 */
	private void setCardEntityBase(CardEntity cardEntity, Card card) {
		cardEntity.setCardId(card.getCardId());
		cardEntity.setUserId(card.getUserId());
		cardEntity.setStatus(card.isStatus());
		cardEntity.setDate(card.getDate());
		cardEntity.setTitle(card.getTitle());
		cardEntity.setIntroduction(cardEntity.getIntroduction());
		cardEntity.setLocation(card.getLocation());
		cardEntity.setUnitType(card.getUnitType());
		for (int i = 0; i < card.getImages().length; i++) {
			imageMapper.insertImage(card.getCardId(), card.getImages()[i]);
		}
	}
	
}
