package com.mbry.IronMan.Mapper;

import com.mbry.IronMan.entity.AdmInfoEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdmInfoMapper {

    /**
     * 
     * @param admId
     * @return
     */
    public AdmInfoEntity queryAdm(String admId);

}
