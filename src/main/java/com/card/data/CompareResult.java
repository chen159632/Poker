package com.card.data;

public class CompareResult {
    int win = 0;

    int lose = 0;
    int equalCount = 0;

    public void addResult(int compareValue) {
        if (compareValue > 0) {
            win++;
        } else if (compareValue < 0) {
            lose++;
        } else {
            equalCount++;
        }

    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getEqualCount() {
        return equalCount;
    }
}
