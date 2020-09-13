package com.mbry.IronMan;

import com.mbry.IronMan.Mapper.CardMapper;
import com.mbry.IronMan.entity.CardEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardMapperTests {
    
    @Autowired
    CardMapper cardMapper;

    @Test
    void test() {
        cardMapper.updateCard(new CardEntity("5548c0aff3fa11ea939bb025aa2bb7a3", null, 0, true, null, null, null, null, 0.0, 0.0, 0.0, 0.0, -1, null, 0, null));
    }
}