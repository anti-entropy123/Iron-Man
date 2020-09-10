package com.mbry.IronMan.Service;

import java.util.ArrayList;
import java.util.List;

import com.mbry.IronMan.Mapper.CardMapper;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.GetCardResponse;
import com.mbry.IronMan.Utils.DateUtil;
import com.mbry.IronMan.entity.CardEntity;
import com.mbry.IronMan.global.Global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmCardService {
    
    @Autowired
    CardMapper cardMapper;

    @Autowired
    DateUtil dateUtil;

    public GetCardResponse.Data[] getCardsByRequire(
        int type,
        String userId,
        long minDate,
        long maxDate,
        int page) {

        int startIndex = (page - 1) * Global.pageSize;
        String minDateS = dateUtil.getDateFromLong(minDate);
        String maxDateS = dateUtil.getDateFromLong(maxDate);
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
}
