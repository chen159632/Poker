package com.card.data;

import java.util.*;

public class Shuffle {

    public  static List<Card> shuffleCards(List<Card> cards) {
        LinkedList<Card> shuffleCards = new LinkedList<>(cards);
        List<Card> afterList = new LinkedList<>();

        while(!shuffleCards.isEmpty()) {
            Random random = new Random(System.currentTimeMillis());

            int next = random.nextInt(shuffleCards.size()) % shuffleCards.size();

            afterList.add(shuffleCards.get(next));
            shuffleCards.remove(next);
        }


        return afterList;
    }
}
