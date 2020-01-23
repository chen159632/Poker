package com.card.patten;

import com.card.data.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoPair extends BasePatten {
    public void apply(List<Card> cardList) {
        super.apply(cardList);
        int pairCount = 0;
        for (int i = 14; i >=2; i--) {
            if (noColorSet[i] == 2) {
                pairCount++;
            }
        }

        if (pairCount >= 2) {
            flag = true;
            setLevel(4);
            Set<Integer> twoPairs = new HashSet<>();
            for (int i = 14; i >= 2; i--) {
                if (noColorSet[i] == 2) {
                    if (twoPairs.size() < 2) {
                        compareList.add(i);
                        twoPairs.add(i);
                    }
                }
            }

            for (int i = 14; i >= 2; i--) {
                if (twoPairs.contains(i)) {
                    continue;
                }
                for (int k = 0; k < noColorSet[i]; k++) {
                    compareList.add(i);
                }
            }
        }
    }
}
