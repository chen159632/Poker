package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestThreeKind {


    @Test
    public void test1() {

        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 10));
        card.add(TestUtil.createCardNormalNumber(Color.HEART, 10));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 8));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 8));

        BasePatten threePatten = new ThreeKind();
        threePatten.apply(card);

        assert 5 == threePatten.getLevel();
        assert 10 == threePatten.getCompareList().get(0);
        assert 14 == threePatten.getCompareList().get(1);
        assert 8 == threePatten.getCompareList().get(2);

    }
}
