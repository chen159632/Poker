package com.card.patten;

import com.card.data.Card;

import java.util.List;

public class FlushPatten extends BasePatten {

    public void apply(List<Card> cardList) {
        super.apply(cardList);

        if (spadeCount >= 5) {
            flag = true;
            for (int i = 14; i >= 2; i--) {
                if (spade[i] > 0) {
                    compareList.add(i);
                }
            }
        } else if (heartCount >= 5) {
            flag = true;
            for (int i = 14; i>= 2; i--) {
                if (heart[i] > 0) {
                    compareList.add(i);
                }
            }
        } else if (diamondCount >= 5) {
            flag = true;
            for (int i = 14; i >= 2; i--) {
                if (diamond[i] > 0) {
                    compareList.add(i);
                }
            }
        } else if (clubCount >= 5) {
            flag = true;
            for (int i = 14; i >= 2; i--) {
                if (club[i] > 0) {
                    compareList.add(i);
                }
            }
        }

        if (isFlag()) {
            setLevel(7);
        }
    }
}
