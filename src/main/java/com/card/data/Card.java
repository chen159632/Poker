package com.card.data;


public class Card {

    private Color color;

    private String number;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color='" + color + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
