package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStraightPatten {

    @Test
    public void testStrainght() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardK(Color.DIAMOND));
        card.add(TestUtil.createCardQ(Color.HEART));
        card.add(TestUtil.createCardJ(Color.HEART));

        StraightPatten straightPatten = new StraightPatten();
        straightPatten.apply(card);

        assert 6 == straightPatten.getLevel();

        List<Integer> compareList = straightPatten.getCompareList();
        assert 14 == compareList.get(0);
        assert 13 == compareList.get(1);
        assert 12 == compareList.get(2);
        assert 11 == compareList.get(3);
        assert 10 == compareList.get(4);

    }
}
