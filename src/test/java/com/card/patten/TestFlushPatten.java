package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFlushPatten {

    @Test
    public void testFlush_1() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 8));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardK(Color.CLUB));

        FlushPatten flushPatten = new FlushPatten();
        flushPatten.apply(card);

        assert 7 == flushPatten.getLevel();

        List<Integer> compareList = flushPatten.getCompareList();
        assert 14 == compareList.get(0);
        assert 13 == compareList.get(1);
        assert 10 == compareList.get(2);
        assert 9 == compareList.get(3);
        assert 8 == compareList.get(4);

    }

    @Test
    public void testFlush_2() {
        Color color = Color.SPADE;
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(color));
        card.add(TestUtil.createCardNormalNumber(color, 10));
        card.add(TestUtil.createCardNormalNumber(color, 8));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        card.add(TestUtil.createCardNormalNumber(color, 2));
        card.add(TestUtil.createCardK(color));

        FlushPatten flushPatten = new FlushPatten();
        flushPatten.apply(card);

        assert 7 == flushPatten.getLevel();

        List<Integer> compareList = flushPatten.getCompareList();
        assert 14 == compareList.get(0);
        assert 13 == compareList.get(1);
        assert 10 == compareList.get(2);
        assert 8 == compareList.get(3);
        assert 2 == compareList.get(4);

    }

    @Test
    public void testFlush_3() {
        Color color = Color.DIAMOND;
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(color));
        card.add(TestUtil.createCardNormalNumber(color, 10));
        card.add(TestUtil.createCardNormalNumber(color, 8));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        card.add(TestUtil.createCardNormalNumber(color, 2));
        card.add(TestUtil.createCardK(color));

        FlushPatten flushPatten = new FlushPatten();
        flushPatten.apply(card);

        assert 7 == flushPatten.getLevel();

        List<Integer> compareList = flushPatten.getCompareList();
        assert 14 == compareList.get(0);
        assert 13 == compareList.get(1);
        assert 10 == compareList.get(2);
        assert 8 == compareList.get(3);
        assert 2 == compareList.get(4);

    }


    @Test
    public void testFlush_4() {
        Color color = Color.HEART;
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(color));
        card.add(TestUtil.createCardNormalNumber(color, 10));
        card.add(TestUtil.createCardNormalNumber(color, 8));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        card.add(TestUtil.createCardNormalNumber(color, 2));
        card.add(TestUtil.createCardK(color));

        FlushPatten flushPatten = new FlushPatten();
        flushPatten.apply(card);

        assert 7 == flushPatten.getLevel();

        List<Integer> compareList = flushPatten.getCompareList();
        assert 14 == compareList.get(0);
        assert 13 == compareList.get(1);
        assert 10 == compareList.get(2);
        assert 8 == compareList.get(3);
        assert 2 == compareList.get(4);

    }
}
