package com.mbry.IronMan.Service;

import com.mbry.IronMan.BusinessObject.Card.*;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.RequestBody.PublishRequestBody.PublishRequest;
import com.mbry.IronMan.Utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    
    @Autowired
	CardDao cardDao;
	
	@Autowired
	DateUtil dateUtil;

    /**
     * 
     * @param publishRequest
     * @return
     */
    public boolean publishCard(PublishRequest publishRequest) {
        return cardDao.createCard(this.getCardFromPR(publishRequest));
    }

    /**
     * 
     */
    private Card getCardFromPR(PublishRequest publishRequest) {
        int type = publishRequest.getType();
		/**
		 * type = 1 出租
		 */
		if (type == 1) {
			return this.getRentCard(publishRequest);
		} 
		/**
		 * type = 2 出售
		 */
		else if (type == 2) {
			return this.getSellCard(publishRequest);
		} 
		/**
		 * type = 3 求租
		 */
		else if (type == 3) {
			return this.getAskRentCard(publishRequest);
		} 
		/**
		 * type = 4 求售
		 */
		else if (type == 4) {
			return this.getAskSellCard(publishRequest);
		} 
		/**
		 * type = 5 求室友
		 */
		else if (type == 5) {
			return this.getRoomMateCard(publishRequest);
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
	private RentCard getRentCard(PublishRequest publishRequest) {
		RentCard card = new RentCard();
		this.setCardBase(publishRequest, card);
		card.setRequirement(publishRequest.getRequire());
		card.setUnionNum(publishRequest.getUnionNum());
		card.setPrice(publishRequest.getPrices()[0]);
		card.setSquare(publishRequest.getSquares()[0]);
		card.setLongitude(publishRequest.getCoordinates()[0]);
		card.setLatitude(publishRequest.getCoordinates()[1]);
		return card;
	}
	
	/**
	 * type = 2
	 * 出售
	 * @param cardEntity
	 * @param card
	 */
	private SellCard getSellCard(PublishRequest publishRequest) {
		SellCard card = new SellCard();
		this.setCardBase(publishRequest, card);
		//card.setRequirement(cardEntity.getRequirement());
		card.setPrice(publishRequest.getPrices()[0]);
		card.setSquare(publishRequest.getSquares()[0]);
		card.setLongitude(publishRequest.getCoordinates()[0]);
		card.setLatitude(publishRequest.getCoordinates()[1]);
		return card;
	}
	
	/**
	 * type = 3 
	 * 求租
	 * @param cardEntity
	 * @param card
	 */
	private AskRentCard getAskRentCard(PublishRequest publishRequest) {
		AskRentCard card = new AskRentCard();
		this.setCardBase(publishRequest, card);
		card.setRequirement(publishRequest.getRequire());
		card.setUnionNum(publishRequest.getUnionNum());
		card.setMinPrice(publishRequest.getPrices()[0]);
		card.setMaxPrice(publishRequest.getPrices()[1]);
		card.setMinSquare(publishRequest.getSquares()[0]);
		card.setMaxSquare(publishRequest.getSquares()[1]);
		return card;
	}
	
	/**
	 * type = 4
	 * 求售
	 * @param cardEntity
	 * @param card
	 */
	private AskSellCard getAskSellCard(PublishRequest publishRequest) {
		AskSellCard card = new AskSellCard();
		this.setCardBase(publishRequest, card);
		card.setMinPrice(publishRequest.getPrices()[0]);
		card.setMaxPrice(publishRequest.getPrices()[1]);
		card.setMinSquare(publishRequest.getSquares()[0]);
		card.setMaxSquare(publishRequest.getSquares()[1]);
		return card;
	}
	
	/**
	 * type = 5
	 * 求室友
	 * @param cardEntity
	 * @param card
	 */
	private RoomMateCard getRoomMateCard(PublishRequest publishRequest) {
		RoomMateCard card = new RoomMateCard();
		this.setCardBase(publishRequest, card);
		card.setMinPrice(publishRequest.getPrices()[0]);
		card.setMaxPrice(publishRequest.getPrices()[1]);
		card.setMinSquare(publishRequest.getSquares()[0]);
		card.setMaxSquare(publishRequest.getSquares()[1]);
		card.setRequirement(publishRequest.getRequire());
		card.setHasHouseResource(publishRequest.getHasHouseResource());
		card.setUnionNum(publishRequest.getUnionNum());
		return card;
	}
	
	/**
	 * 
	 * @param cardEntity
	 * @param card
	 */
	private void setCardBase(PublishRequest publishRequest, Card card) {
		card.setCardId(null);
		card.setUserId(publishRequest.getUserId());
		card.setStatus(false);
		card.setDate(dateUtil.getDate());
		card.setTitle(publishRequest.getTitle());
		card.setIntroduction(publishRequest.getIntroduce());
		card.setLocation(publishRequest.getLocation());
		card.setUnitType(publishRequest.getUnitType());
		card.setImages(publishRequest.getImages());
	}
}
