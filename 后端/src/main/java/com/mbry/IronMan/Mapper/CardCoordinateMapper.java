package com.mbry.IronMan.Mapper;

import com.mbry.IronMan.entity.CardCoordinateEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardCoordinateMapper {

    public void insert(CardCoordinateEntity cardCoordinateEntity);

    public void deleteByCardId(String cardId);

    public CardCoordinateEntity queryByCardId(String cardId);
    
}
