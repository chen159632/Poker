package com.card.data;

import com.card.patten.BasePatten;
import com.card.patten.PatternBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Assignee {

    private List<Card> playA;

    private BasePatten playAPattern;

    private BasePatten playBPattern;

    private List<Card> playB;

    private List<Card> openList;

    private int compareResult = 0;

    private List<Card> nextTwoCardsList = new ArrayList<>();


    public Assignee() {
        // empty.
    }

    public int getCompareResult() {
        return compareResult;
    }

    public void setCompareResult(int compareResult) {
        this.compareResult = compareResult;
    }

    public Assignee(List<Card> cardList) {
        playA = new ArrayList<>(2);
        playB = new ArrayList<>(2);

        openList = new ArrayList<>(3);


        playA.add(cardList.get(0));

        playB.add(cardList.get(1));
        playA.add(cardList.get(2));
        playB.add(cardList.get(3));

        for (int i = 4; i < 7; i++) {
            openList.add(cardList.get(i));
        }

        //// 两张暗牌，最后比较大小的时候，是从7张里面选取最大的5张比较结果。 playA 只能看到手里的2张和开放的三张牌。
        nextTwoCardsList.add(cardList.get(7));
        nextTwoCardsList.add(cardList.get(8));

    }

    public int comparePlayer() {
        List<Card> playACards = new ArrayList<>(playA);
        playACards.addAll(openList);
        playACards.addAll(nextTwoCardsList);

        List<Card> playAVisibleCards = new ArrayList<>(playA);
        playAVisibleCards.addAll(openList);

        List<Card> playBCards = new ArrayList<>(playB);
        playBCards.addAll(openList);
        playBCards.addAll(nextTwoCardsList);

        List<Card> playBVisibleCards = new ArrayList<>(playB);
        playBVisibleCards.addAll(openList);


        playAPattern = PatternBuilder.findPattern(playAVisibleCards);
        playBPattern = PatternBuilder.findPattern(playBVisibleCards);

        BasePatten playAFinalPattern = PatternBuilder.findPattern(playACards);
        BasePatten playBFinalPattern = PatternBuilder.findPattern(playBCards);

        if (playAFinalPattern.getLevel() > playBFinalPattern.getLevel()) {
            return 1;
        } else if (playAFinalPattern.getLevel() < playBFinalPattern.getLevel()) {
            return -1;
        } else {
            List<Integer> playACompareList = playAFinalPattern.getCompareList();
            List<Integer> playBCompareList = playBFinalPattern.getCompareList();
            try {
                for (int i = 0; i < playACompareList.size() && i < 5; i++) {
                    if (playACompareList.get(i) > playBCompareList.get(i)) {
                        return 1;
                    } else if (playACompareList.get(i) < playBCompareList.get(i)) {
                        return -1;
                    }
                }
            } catch (Exception e) {
                System.out.println(playACards);
                System.out.println(playAFinalPattern);

                System.out.println(playBCards);
                System.out.println(playBFinalPattern);
                System.out.println("---");
                e.printStackTrace();
            }
        }
        return 0;
    }



    public List<Card> getPlayA() {
        return playA;
    }

    public List<Card> getPlayB() {
        return playB;
    }

    public List<Card> getOpenList() {
        return openList;
    }

    public BasePatten getPlayAPattern() {
        return playAPattern;
    }

    public BasePatten getPlayBPattern() {
        return playBPattern;
    }

    public void setPlayA(List<Card> playA) {
        this.playA = playA;
    }

    public void setPlayAPattern(BasePatten playAPattern) {
        this.playAPattern = playAPattern;
    }

    public void setPlayBPattern(BasePatten playBPattern) {
        this.playBPattern = playBPattern;
    }

    public void setPlayB(List<Card> playB) {
        this.playB = playB;
    }

    public void setOpenList(List<Card> openList) {
        this.openList = openList;
    }

    public List<Card> getNextTwoCardsList() {
        return nextTwoCardsList;
    }

    public void setNextTwoCardsList(List<Card> nextTwoCardsList) {
        this.nextTwoCardsList = nextTwoCardsList;
    }
}
