package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mbry.IronMan.entity.CardEntity;

@Mapper
public interface CardMapper {
	
	/**
	 * 插入一条新的Card
	 * @param cardEntity
	 */
	public void insertCard(CardEntity cardEntity);
	
	/**
	 * 删除指定的card
	 * @param cardId
	 */
	public void deleteCardById(String cardId);
	
	/**
	 * 返回指定id的Card信息
	 * @param cardId
	 * @return
	 */
	public CardEntity queryCardByCardId(String cardId);
	
	/**
	 * 查询符合条件的Cards
	 * @param userId
	 * @param status
	 * @return
	 */
	public CardEntity[] queryCardByUserIdAndStatus(@Param("userId")String userId, @Param("status")Boolean status);
	
	/**
	 * 查询符合条件的Card
	 * @param startIndex
	 * @param pageSize
	 * @param type
	 * @param location
	 * @param minPrice
	 * @param maxPrice
	 * @param minSquare
	 * @param maxSquare
	 * @param unitType
	 * @return
	 */
	public CardEntity[] queryCards(
		@Param("startIndex")int startIndex, 
		@Param("pageSize")int pageSize,
		@Param("type")int type, 
		@Param("location")String location, 
		@Param("minPrice")Double minPrice, 
		@Param("maxPrice")Double maxPrice,
		@Param("minSquare")Double minSquare,
		@Param("maxSquare")Double maxSquare,
		@Param("unitType")int unitType,
		@Param("hasHouseResource")Boolean hasHouseResource);
	
	/**
	 * 查询card的发起者
	 * @param cardId
	 * @return
	 */
	public String queryUserIdById(String cardId);

	public CardEntity[] queryCardsForAdm(
		@Param("type")int type, 
		@Param("startIndex")int startIndex, 
		@Param("pageSize")int pageSize, 
		@Param("userId")String userId, 
		@Param("minDate")String minDate, 
		@Param("maxDate")String maxDate);

	public int queryCardsPagesForAdm(
		@Param("startIndex")int startIndex, 
		@Param("pageSize")int pageSize, 
		@Param("userId")String userId, 
		@Param("minDate")String minDate, 
		@Param("maxDate")String maxDate);

	public CardEntity[] queryRentSell();
	
	/**
	 * 更新一条指定的Card
	 * @param cardEntity
	 */
	public void updateCard(CardEntity cardEntity);

	/**
	 * 将指定Card状态更新为true
	 * @param cardId
	 */
	public void finsihCard(String cardId);
	
}
