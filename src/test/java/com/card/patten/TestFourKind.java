package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFourKind {

    @Test
    public void testFourKind() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 10));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));

        FourKind fourKind = new FourKind();
        fourKind.apply(card);

        assert 9 == fourKind.getLevel();
        assert 10 == fourKind.getCompareList().get(0);
        assert 14 == fourKind.getCompareList().get(1);

    }

    @Test
    public void testFourKind_2() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 10));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 9));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 9));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 9));

        FourKind fourKind = new FourKind();
        fourKind.apply(card);

        assert 9 == fourKind.getLevel();
        assert 10 == fourKind.getCompareList().get(0);
        assert 14 == fourKind.getCompareList().get(1);

    }
}
