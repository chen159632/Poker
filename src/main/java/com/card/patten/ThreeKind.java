package com.card.patten;

import com.card.data.Card;

import java.util.List;

public class ThreeKind extends BasePatten {
    public void apply(List<Card> cardList) {
        super.apply(cardList);
        int threeTimes = 0;
        for (int i = 14; i >=2; i--) {
            if (threeTimes == 0 && noColorSet[i] == 3) {
                threeTimes = i;
                flag = true;
            }
        }

        if (flag) {
            setLevel(5);
            compareList.add(threeTimes);

            for (int i = 14; i >= 2; i--) {
                if (i != threeTimes  && noColorSet[i] > 0) {
                    compareList.add(i);
                }
            }
        }
    }
}
