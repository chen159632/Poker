package com.card.patten;


import java.util.*;

import com.card.data.Card;
import com.card.data.Color;
import org.junit.Test;

public class TestStraigntFlush {


    @Test
    public void testStraingtFlush_1() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardQ(Color.CLUB));

        card.add(TestUtil.createCardK(Color.CLUB));
        card.add(TestUtil.createCardJ(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));

        StraigntFlush straigntFlushPatten = new StraigntFlush();
        straigntFlushPatten.apply(card);

        assert true == straigntFlushPatten.isFlag();

        assert 14 == straigntFlushPatten.getMaxNumber();

        assert 14 == straigntFlushPatten.getCompareList().get(0);
        assert 10 == straigntFlushPatten.getCompareList().get(4);

    }

    @Test
    public void testStraingtFlush_2() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 5));

        StraigntFlush straigntFlushPatten = new StraigntFlush();
        straigntFlushPatten.apply(card);

        assert true == straigntFlushPatten.isFlag();

        assert 14 == straigntFlushPatten.getMaxNumber();

        assert 5 == straigntFlushPatten.getCompareList().get(0);
        assert 1 == straigntFlushPatten.getCompareList().get(4);

    }

    @Test
    public void testStraingtFlush_3() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 5));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 6));


        StraigntFlush straigntFlushPatten = new StraigntFlush();
        straigntFlushPatten.apply(card);

        assert true == straigntFlushPatten.isFlag();

        assert 6 == straigntFlushPatten.getCompareList().get(0);
        assert 2 == straigntFlushPatten.getCompareList().get(4);
    }

    @Test
    public void testStraingtFlush_4() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardA(Color.CLUB));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 5));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 6));


        StraigntFlush straigntFlushPatten = new StraigntFlush();
        straigntFlushPatten.apply(card);

        assert true == straigntFlushPatten.isFlag();

        assert 6 == straigntFlushPatten.getCompareList().get(0);
        assert 2 == straigntFlushPatten.getCompareList().get(4);
    }


    @Test
    public void testStraingtFlush_failure_1() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 5));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 6));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 7));


        StraigntFlush straigntFlushPatten = new StraigntFlush();
        straigntFlushPatten.apply(card);

        assert false == straigntFlushPatten.isFlag();

        assert 0 == straigntFlushPatten.getCompareList().size();
    }

    @Test
    public void testStraingtFlush_failure_2() {
        List<Card> card = new ArrayList<Card>();
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        card.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 3));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 5));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 6));
        card.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));


        StraigntFlush straigntFlushPatten = new StraigntFlush();
        straigntFlushPatten.apply(card);

        assert false == straigntFlushPatten.isFlag();

        assert 0 == straigntFlushPatten.getCompareList().size();
    }

}
