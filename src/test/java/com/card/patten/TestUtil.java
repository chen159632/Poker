package com.card.patten;

import com.card.data.Card;
import com.card.data.Color;

public class TestUtil {

    public static Card createCardJ(Color color) {
        Card card = new Card();
        card.setNumber("J");
        card.setColor(color);
        return card;
    }

    public static Card createCardQ(Color color) {
        Card card = new Card();
        card.setNumber("Q");
        card.setColor(color);
        return card;
    }

    public static Card createCardK(Color color) {
        Card card = new Card();
        card.setNumber("K");
        card.setColor(color);
        return card;
    }

    public static Card createCardA(Color color) {
        Card card = new Card();
        card.setNumber("A");
        card.setColor(color);
        return card;
    }

    public static Card createCardNormalNumber(Color color, int number) {
        Card card = new Card();
        card.setNumber(String.valueOf(number));
        card.setColor(color);
        return card;
    }

}
