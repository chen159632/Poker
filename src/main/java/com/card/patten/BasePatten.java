package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePatten {

    protected int[] spade = new int[16];
    protected int[] heart = new int[16];
    protected int[] diamond = new int[16];
    protected int[] club = new int[16];

    protected int spadeCount;
    protected int heartCount;
    protected int diamondCount;
    protected int clubCount;

    protected int[] noColorSet = new int[16];

    protected int maxNumber;

    protected int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    protected List<Integer> compareList = new ArrayList<>();

    public List<Integer> getCompareList() {
        return compareList;
    }

    protected boolean flag;

    public boolean isFlag() {
        return flag;
    }

    protected void checkStraintFlushColor(int[] numnberArray) {
        for (int i = 14; i >= 5; i --) {
            if (numnberArray[i] > 0) {
                int count = 0;
                int j = i - 1;
                while(numnberArray[j] > 0) {
                    j --;
                }
                if (i - j >= 5) {
                    flag = true;

                    for (int k = i; k > j ;k--) {
                        compareList.add(k);
                    }
                    break;
                }
                i = j;
            }
        }
    }

    public static List<Integer> convertCardToNumber(Card card) {
        List<Integer> ret = new ArrayList<>(2);
        if ("J".equals(card.getNumber())) {
            ret.add(11);
        } else if ("Q".equals(card.getNumber())) {
            ret.add(12);
        } else if ("K".equals(card.getNumber())) {
            ret.add(13);
        } else if ("A".equals(card.getNumber())) {
            ret.add(1);
            ret.add(14);
        } else ret.add(Integer.parseInt(card.getNumber()));

        return ret;
    }

    public static void applyColorSet(Card card, int[] array) {
        List<Integer> cardNumbers = convertCardToNumber(card);
        for (Integer index : cardNumbers) {
            array[index]++;
        }
    }

    protected void apply(List<Card> cardList) {
        for (Card card : cardList) {
            if (Color.SPADE.equals(card.getColor())) {
                spadeCount++;
                applyColorSet(card, spade);
            } else if (Color.HEART.equals(card.getColor())) {
                heartCount++;
                applyColorSet(card, heart);
            } else if (Color.DIAMOND.equals(card.getColor())) {
                diamondCount++;
                applyColorSet(card, diamond);
            } else if (Color.CLUB.equals(card.getColor())) {
                clubCount++;
                applyColorSet(card, club);
            }

            applyColorSet(card, noColorSet);
        }
        for (int i = 2; i < noColorSet.length; i++) {
            if (noColorSet[i] > 0) {
                maxNumber = i;
            }
        }
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public String toString() {
        return "BasePatten{" +
                "level=" + level +
                ", compareList=" + compareList +
                ", flag=" + flag +
                '}';
    }

    public void setCompareList(List<Integer> compareList) {
        this.compareList = compareList;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
