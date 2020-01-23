package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPair {


    @Test
    public void testPair() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 2));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 7));
        card.add(TestUtil.createCardK(Color.DIAMOND));

        BasePatten pair = new Pair();
        pair.apply(card);

        assert 3 == pair.getLevel();
        assert 2 == pair.getCompareList().get(0);
        assert 13 == pair.getCompareList().get(1);
        assert 10 == pair.getCompareList().get(2);
        assert 8 == pair.getCompareList().get(3);
        assert 7 == pair.getCompareList().get(4);

    }



}
