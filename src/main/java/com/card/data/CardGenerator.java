package com.card.data;

import java.util.*;

public class CardGenerator {

    private static Card createCard(Color color, String number) {
        Card card = new Card();
        card.setNumber(number);
        card.setColor(color);
        return card;
    }

    public static List<Card> generateColorSet(Color color) {
        List<Card> cards = new ArrayList<>();

        for (int i = 2; i <= 10; i++) {
            Card card = new Card();
            card.setColor(color);
            card.setNumber(String.valueOf(i));
            cards.add(card);
        }
        cards.add(createCard(color, "J"));
        cards.add(createCard(color, "Q"));
        cards.add(createCard(color, "K"));
        cards.add(createCard(color, "A"));

        return cards;
    }

    public static List<Card> generateCard() {
        List<Card> cardList = new ArrayList<>();

        cardList.addAll(generateColorSet(Color.SPADE));
        cardList.addAll(generateColorSet(Color.HEART));
        cardList.addAll(generateColorSet(Color.DIAMOND));
        cardList.addAll(generateColorSet(Color.CLUB));

        return cardList;
    }
}
