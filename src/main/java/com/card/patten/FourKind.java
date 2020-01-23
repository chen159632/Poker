package com.card.patten;

import com.card.data.Card;

import java.util.List;

public class FourKind extends BasePatten {


    public void apply(List<Card> cardList) {
        super.apply(cardList);
        for (int i = 14; i >= 2; i--) {
            if (noColorSet[i] == 4) {
                flag = true;
                compareList.add(i);
                break;
            }
        }

        if (flag) {
            for (int i = 14; i >= 2; i--) {
                if (noColorSet[i] != 4 && noColorSet[i] > 0) {
                    compareList.add(i);
                }
            }
            setLevel(9);
        }
    }
}
