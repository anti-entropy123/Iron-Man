package com.mbry.IronMan.Service;

import java.util.ArrayList;
import java.util.List;

import com.mbry.IronMan.Mapper.CardMapper;
import com.mbry.IronMan.Mapper.UserMapper;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.GetCardResponse;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.GetUserResponse;
import com.mbry.IronMan.Utils.DateUtil;
import com.mbry.IronMan.entity.CardEntity;
import com.mbry.IronMan.entity.UserEntity;
import com.mbry.IronMan.global.Global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmCardService {
    
    @Autowired
    CardMapper cardMapper;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    UserMapper userMapper;

    public GetCardResponse.Data[] getCardsByRequire(
        int type,
        String userId,
        long minDate,
        long maxDate,
        int page) {

        int startIndex = (page - 1) * Global.pageSize;
        String minDateS;
        String maxDateS;
        if (minDate > 0) {
            minDateS = dateUtil.getDateFromLong(minDate);
        } else {
            minDateS = null;
        }
        if (maxDate > 0) {
            maxDateS = dateUtil.getDateFromLong(maxDate);
        } else {
            maxDateS = null;
        }
        CardEntity[] cardEntitys = cardMapper.queryCardsForAdm(startIndex, Global.pageSize, userId, minDateS, maxDateS);

        GetCardResponse getCardResponse = new GetCardResponse();
        List<GetCardResponse.Data> datas = new ArrayList<GetCardResponse.Data>();
        for (CardEntity cardEntity: cardEntitys) {
            GetCardResponse.Data data = getCardResponse.new Data();
            data.setCardId(cardEntity.getCardId());
            data.setType(cardEntity.getType());
            data.setUserId(cardEntity.getUserId());
            data.setDate(cardEntity.getDate());
            data.setTitle(cardEntity.getTitle());
            datas.add(data);
        }

        return datas.toArray(new GetCardResponse.Data[datas.size()]);
    }

    public int getCardsPagesByRequire(
        int type,
        String userId,
        long minDate,
        long maxDate,
        int page) {
        int startIndex = (page - 1) * Global.pageSize;
        String minDateS = dateUtil.getDateFromLong(minDate);
        String maxDateS = dateUtil.getDateFromLong(maxDate);
        int pages = cardMapper.queryCardsPagesForAdm(startIndex, Global.pageSize, userId, minDateS, maxDateS);
        if (pages % Global.pageSize > 0) {
            pages = pages / Global.pageSize + 1;
        } else {
            pages = pages % Global.pageSize;
        }
        return pages;
    }

    public void deleteCards(String[] cardIds) {
        for (String cardId: cardIds) {
            cardMapper.deleteCardById(cardId);
        }
    }

    public GetUserResponse getUser(
        String nickname,
        String userId,
        String mobileNumber,
        int page) {
        int startIndex = (page - 1) * Global.pageSize;
        try {
            UserEntity[] userEntitys = userMapper.queryUsers(startIndex, Global.pageSize, nickname, userId, mobileNumber);
            if (userEntitys == null) {
                return new GetUserResponse(null, 0, 0, "no users");
            } 
            GetUserResponse getUserResponse = new GetUserResponse();
            List<GetUserResponse.Data> datas = new ArrayList<GetUserResponse.Data>();
            for (UserEntity userEntity: userEntitys) {
                GetUserResponse.Data data = getUserResponse.new Data(
                    userEntity.getUserId(),
                    userEntity.getNickName(),
                    userEntity.getAvatar(),
                    userEntity.getSex(),
                    userEntity.getIntroduction(),
                    userEntity.getNickName()
                );
                datas.add(data);
            }
            GetUserResponse.Data[] datasA = datas.toArray(new GetUserResponse.Data[datas.size()]);
            int totalPage = userMapper.queryUserPage(nickname, userId, mobileNumber);
            if ((totalPage % Global.pageSize) > 0) {
                totalPage = totalPage / Global.pageSize + 1;
            } else {
                totalPage = totalPage / Global.pageSize;
            }
            return new GetUserResponse(datasA, totalPage, 1, "mbrynb");
        } catch (Exception e){
            e.printStackTrace();
            return new GetUserResponse(null, 0, 0, "server error");
        }
    }

    public DefaultResponse deleteUser(String[] userIds) {
        try {
            for (String userId: userIds) {
                userMapper.deleteUser(userId);
            }
            return new DefaultResponse(1, "mbrynb");
        } catch(Exception e) {
            e.printStackTrace();
            return new DefaultResponse(0, "server error");
        }
    }
    
}
