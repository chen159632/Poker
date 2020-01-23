package com.card.patten;

import com.card.data.Card;

import java.util.ArrayList;
import java.util.List;

public class PatternBuilder {


    public static BasePatten findPattern(List<Card> cardList) {
        List<BasePatten> pattenList = new ArrayList<>();
        pattenList.add(new StraigntFlush());
        pattenList.add(new FourKind());
        pattenList.add(new FourHource());
        pattenList.add(new FlushPatten());
        pattenList.add(new StraightPatten());
        pattenList.add(new ThreeKind());
        pattenList.add(new TwoPair());
        pattenList.add(new Pair());
        pattenList.add(new HighCard());

        for (BasePatten patten : pattenList) {
            patten.apply(cardList);
            if (patten.isFlag()) {
                return patten;
            }
        }

        throw new IllegalStateException("should not reach here");
    }
}
