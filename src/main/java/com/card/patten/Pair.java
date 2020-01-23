package com.card.patten;

import com.card.data.Card;

import java.util.List;

public class Pair extends BasePatten {

    public void apply(List<Card> cardList) {
        super.apply(cardList);
        int pairNumber = 0;
        for (int i = 14; i >=2; i--) {
            if (pairNumber == 0 && noColorSet[i] == 2) {
                pairNumber = i;
                break;
            }
        }

        if (pairNumber > 0) {
            compareList.add(pairNumber);
            flag = true;
            setLevel(3);

            for (int i = 14; i >= 2; i--) {
                if (i == pairNumber) {
                    continue;
                }
                if (noColorSet[i] > 0) {
                    compareList.add(i);
                }
            }
        }
    }
}
