package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHighCard {

    @Test
    public void testHighCard() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 5));

        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.SPADE, 7));
        card.add(TestUtil.createCardK(Color.DIAMOND));

        HighCard highCard = new HighCard();
        highCard.apply(card);

        assert 1 == highCard.getLevel();

        assert 13 == highCard.getCompareList().get(0);
        assert 10 == highCard.getCompareList().get(1);
        assert 8 == highCard.getCompareList().get(2);
        assert 7 == highCard.getCompareList().get(3);
        assert 5 == highCard.getCompareList().get(4);

    }
}
