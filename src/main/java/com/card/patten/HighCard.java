package com.card.patten;

import com.card.data.Card;

import java.util.List;

public class HighCard extends BasePatten {
    public void apply(List<Card> cardList) {
        super.apply(cardList);

        flag = true;
        setLevel(1);

        for (int i = 14; i >= 2; i--) {
            if (noColorSet[i] > 0) {
                compareList.add(i);
            }
        }
    }
}
