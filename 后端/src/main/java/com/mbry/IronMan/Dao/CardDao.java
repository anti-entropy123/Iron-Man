package com.mbry.IronMan.Dao;

import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Card.AskRentCard;
import com.mbry.IronMan.BusinessObject.Card.AskSellCard;
import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.BusinessObject.Card.RentCard;
import com.mbry.IronMan.BusinessObject.Card.RoomMateCard;
import com.mbry.IronMan.BusinessObject.Card.SellCard;

@Service
public interface CardDao {
    /**
     * 
     * @param cardId
     * @return
     */
    public Card queryCardByCardId(String cardId);

    /**
     * 查询某用户发起的所有帖子
     * @param userId
     * @return
     */
    public Card[] queryCardsByUserId(String userId);

    /**
     * 查询该用户发起的所有已完成的帖子
     * @param userId
     * @return
     */
    public Card[] queryFinishCardsByUserId(String userId);

    /**
     * 获取所有类别的最新帖子, 按页返回, 可以不对坐标进行初始化
     * @param page
     * @return 
     */
    public Card[] queryALLCards(int page);

    /**
     * 出租贴
     * @param page 
     * @param location 不需要时为 null
     * @param priceRange 价格区间, 不需要时为null
     * @param unitType 户型, 不需要时为 -1
     * @return
     */
    public RentCard[] queryRentCards(int page, String location, Double[] priceRange, Double[] squares, int unitType);
    /**
     * 
     * @param page
     * @param location
     * @param priceRange
     * @param unitType
     * @return
     */
    public AskRentCard[] queryAskRentCards(int page, String location, Double[] priceRange, Double[] squares, int unitType);
    /**
     * 
     * @param page
     * @param location
     * @param priceRange
     * @param unitType
     * @return
     */
    public SellCard[] querySellCards(int page, String location, Double[] priceRange, Double[] squares, int unitType);
    /**
     * 
     * @param page
     * @param location
     * @param priceRange
     * @param unitType
     * @return
     */
    public AskSellCard[] queryAskSellCards(int page, String location, Double[] priceRange, Double[] squares, int unitType);
    
    /**
     * 
     * @param page
     * @param location
     * @param priceRange
     * @param unitType
     * @return
     */
    public RoomMateCard[] queryAskRoomMateCards(int page, String location, Double[] priceRange, Double[] squares, int unitType, Boolean hasHouseResource);
    
    
    /**
     * 新建一个帖子
     * @param card
     * @return
     */
    public Boolean createCard(Card card);
    
    /**
     * 根据cardId更新数据
     * @param card
     * @return
     */
    public Boolean updateCard(Card card);

    /**
     * 根据cardId, 将帖子状态更新为已完成
     * @param cardId
     * @return
     */
    public Boolean finishCard(String cardId);

    /**
     * 为某个用户记录未完成的订单
     * @param userId
     * @param cardId
     * @return
     */
    public Boolean recordUncompleteCardForUser(String userId, String cardId);

    /**
     * 删去某用户未完成订单的记录
     * @param userId
     * @param cardId
     * @return
     */
    public Boolean deleteUncompleteCardForUser(String userId, String cardId);

    /**
     * 当前端请求查询用户参与的未完成的订单时, 通过此接口查询用户所参与的未完成订单(不包括自己发起的).
     * @param userId
     * @return
     */
    public Card[] queryUncompleteCardsByUserId(String userId);

    /**
     * 将帖子的状态设置为完成
     * @param cardId
     */
    public void setStatusTrue(String cardId);

    /**
     * 返回带有坐标的Card数组
     * @return
     */
    public Card[] queryCardsWithCoordinates();
}