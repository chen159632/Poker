package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;

import java.util.List;

public class FeaturePattern extends BasePatten {

    public FeaturePattern(List<Card> openCards) {
        apply(openCards);
    }


    public int getNoColorCardCount(int cardNumber) {
        return noColorSet[cardNumber];
    }

    public int getSameCardNumber(Card card) {
       List<Integer> cardNumbers = convertCardToNumber(card);

       for (int i = 14; i >= 1; i--) {
           if (noColorSet[i] > 0 && cardNumbers.contains(i)) {
               return noColorSet[i];
           }
       }
       return 0;
    }

    public int getColorNumber(Color color) {
        if (Color.CLUB.equals(color)) {
            return clubCount;
        } else if (Color.SPADE.equals(color)) {
            return spadeCount;
        } else if (Color.HEART.equals(color)) {
            return heartCount;
        } else if (Color.DIAMOND.equals(color)) {
            return diamondCount;
        }

        return 0;
    }

    public int[] getNonColorArray() {
        int[] ret = new int[16];
        for (int i = 1; i < 16; i++) {
            ret[i] = noColorSet[i];
        }
        return ret;
    }

    public int[] getColorArray(Color color) {
        int[] ret = new int[16];
        if (Color.SPADE.equals(color)) {
            for (int i = 1; i < 16; i++) {
                ret[i] = spade[i];
            }
        } else if (Color.HEART.equals(color)) {
            for (int i = 1; i < 16; i++) {
                ret[i] = heart[i];
            }
        } else if (Color.CLUB.equals(color)) {
            for (int i = 1; i < 16; i++) {
                ret[i] = club[i];
            }
        } else if (Color.DIAMOND.equals(color)) {
            for (int i = 1; i < 16; i++) {
                ret[i] = diamond[i];
            }
        }
        return ret;
    }


    public int getMaxSequence(int[] numberArray, List<Integer> givenCardNumbers) {
        for (Integer givenNumbers : givenCardNumbers) {
            numberArray[givenNumbers] ++;
        }
        int maxSequence = 0;
        for (int i = 14; i >= 3; i --) {
            if (numberArray[i] > 0) {
                int count = 0;
                int j = i - 1;
                while(j >= 1 && numberArray[j] > 0) {
                    j --;
                }
                if (i - j >= 3) {
                    boolean containGivenNumber = false;
                    for (int k = i; k >= j; k --) {
                        if (givenCardNumbers.contains(k)) {
                            containGivenNumber = true;
                            break;
                        }
                    }
                    if (containGivenNumber) {
                        maxSequence = Math.max(maxSequence, i - j);
                    }
                }
                i = j;
            }
        }
        return maxSequence;
    }

}
