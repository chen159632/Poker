package com.card.patten;

import com.card.data.Card;

import java.util.List;

public class FourHource extends BasePatten {
    public void apply(List<Card> cardList) {
        super.apply(cardList);

        int threeCardNumber = 0;
        int twoCardNumber = 0;

        for (int i = 14; i >= 2; i--) {
            if (threeCardNumber == 0 && noColorSet[i] == 3) {
                threeCardNumber = i;
                noColorSet[i] -= 3;
            }
        }

        for (int i = 14; i >= 2; i--) {
            if (twoCardNumber == 0 && noColorSet[i] >= 2) {
                twoCardNumber = i;
                noColorSet[i] -= 2;
            }
        }


        if (threeCardNumber > 0 && twoCardNumber > 0) {
            flag = true;
            compareList.add(threeCardNumber);
            compareList.add(twoCardNumber);
        }

        if (isFlag()) {
            setLevel(8);
        }
    }
}
