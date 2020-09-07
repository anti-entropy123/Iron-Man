package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {
	
	/**
	 * 插入一条映射
	 * @param cardId
	 * @param imageUrl
	 */
	public void insertImage(@Param("cardId")String cardId, @Param("imageUrl")String imageUrl);
	
	/**
	 * 查询某Card的全部图片
	 * @param cardId
	 * @return
	 */
	public String[] queryImageByCardId(String cardId);
	
	/**
	 * 查询某Card的第一张图片
	 * @param cardId
	 * @return
	 */
	public String queryFirstImageByCardId(String cardId);
	
	/**
	 * 删除某Card的全部图片
	 * @param cardId
	 */
	public void deleteImageByCardId(String cardId);
	
}
