package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestTwoPair {


    @Test
    public void testTwoPair1() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 7));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 8));

        BasePatten twoPair = new TwoPair();
        twoPair.apply(card);

        assert 4 == twoPair.getLevel();
        assert 10 == twoPair.getCompareList().get(0);
        assert 8 == twoPair.getCompareList().get(1);
        assert 7 == twoPair.getCompareList().get(2);

    }


    @Test
    public void testPair_2() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 7));

        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 8));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 7));

        BasePatten pair = new TwoPair();
        pair.apply(card);

        assert 4 == pair.getLevel();
        assert 10 == pair.getCompareList().get(0);
        assert 8 == pair.getCompareList().get(1);
        assert 7 == pair.getCompareList().get(2);
        assert 7 == pair.getCompareList().get(3);
        assert 2 == pair.getCompareList().get(4);
    }
}
