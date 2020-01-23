package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFourHource {

    @Test
    public void testFourHource() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 10));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 7));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));

        FourHource fourHource = new FourHource();
        fourHource.apply(card);

        List<Integer> compareList = fourHource.getCompareList();
        assert 8 == fourHource.getLevel();

        assert 10 == compareList.get(0);
        assert 7 == compareList.get(1);
    }

    @Test
    public void testFourHource_2() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 10));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 7));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 7));

        FourHource fourHource = new FourHource();
        fourHource.apply(card);

        List<Integer> compareList = fourHource.getCompareList();
        assert 8 == fourHource.getLevel();

        assert 10 == compareList.get(0);
        assert 7 == compareList.get(1);
    }

    @Test
    public void testFourHource_Failure_1() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 10));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));

        FourHource fourHource = new FourHource();
        fourHource.apply(card);

        List<Integer> compareList = fourHource.getCompareList();
        assert 0 == fourHource.getLevel();

        assert false == fourHource.isFlag();
    }
}
