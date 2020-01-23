package com.card.feature;

import com.card.data.Card;
import com.card.data.Color;
import com.card.patten.BasePatten;
import com.card.patten.FeaturePattern;
import com.card.patten.PatternBuilder;

import java.util.*;

public class FeatureGenerator {

    private static int convertCardToNumber(Card card) {
        if ("A".equals(card.getNumber())) {
            return 14;
        } else if ("K".equals(card.getNumber())) {
            return 13;
        } else if ("Q".equals(card.getNumber())) {
            return 12;
        } else if ("J".equals(card.getNumber())) {
            return 11;
        } else {
            return Integer.parseInt(card.getNumber());
        }
    }

    private static void calculateBaseFeature(List<Card> cardList, Map<String, Double> features) {
        Card firstCard = cardList.get(0);
        double firstNumber = convertCardToNumber(firstCard);
        Card secondCard = cardList.get(1);
        double secondNumber = convertCardToNumber(secondCard);

        features.put("owner_max_card", Math.max(firstNumber, secondNumber));
        features.put("owner_min_card", Math.min(firstNumber, secondNumber));
        features.put("owner_sum_card", (firstNumber + secondNumber));
        features.put("owner_avg_card", (firstNumber + secondNumber) / 2.0);

        if (Double.compare(firstNumber, secondNumber) == 0) {
            features.put("owner_is_pair", 1.0);
            features.put("owner_pair_number", firstNumber);
        } else {
            features.put("owner_is_pair", 0.0);
            features.put("owner_pair_number", -999999.0);
        }

        features.put("owner_same_color", firstCard.getColor().equals(secondCard.getColor()) ? 1.0 : 0.0);
        features.put("owner_number_diff", Math.abs(firstNumber - secondNumber));
        features.put("owner_number_is_sequence", Math.abs(firstNumber - secondNumber) == 1 ? 1.0 : 0.0);
    }

    private static void calculateFeatureForOneCardWithOpenCards(List<Card> ownerCards, List<Card> openCards, Map<String, Double> features) {
        calculate4CardFeatures(ownerCards.get(0), openCards, features, "first_card_mix");

        calculate4CardFeatures(ownerCards.get(1), openCards, features, "second_card_mix");
    }

    private static void calculate4CardFeatures(Card card, List<Card> openCardList, Map<String, Double> features, String prefix) {
        FeaturePattern pattern = new FeaturePattern(openCardList);

        int cardNumber = convertCardToNumber(card);

        if (pattern.getNoColorCardCount(cardNumber) == 1) {
            features.put(prefix + "_has_pair", 1.0);
            features.put(prefix + "_has_pair_number", (double) cardNumber);
        } else {
            features.put(prefix + "_has_pair", 0.0);
            features.put(prefix + "_has_pair_number", -999999.0);
        }

        if (pattern.getNoColorCardCount(cardNumber) == 2) {
            features.put(prefix + "_has_three_card", 1.0);
            features.put(prefix + "_has_three_card_number",(double) cardNumber);
        } else {
            features.put(prefix + "_has_three_card", 0.0);
            features.put(prefix + "_has_three_card_number",-999999.0);
        }

        if (pattern.getNoColorCardCount(cardNumber) == 3) {
            features.put(prefix +" _has_four_card", 1.0);
            features.put(prefix +" _has_four_card_number", (double) cardNumber);
        } else {
            features.put(prefix +" _has_four_card", 0.0);
            features.put(prefix +" _has_four_card_number", -999999.0);
        }

        int maxColorCount = 0;
        for (Color eachColor : Color.values()) {
            int openCardColorCount = pattern.getColorNumber(eachColor);
            if (eachColor.equals(card.getColor())) {
                maxColorCount = Math.max(maxColorCount, openCardColorCount + 1);
            }
        }
        features.put(prefix + "_max_color_count", (double) maxColorCount);
        features.put(prefix + "_has_4_same_color", (maxColorCount >= 4 ? 1.0 : 0.0));
        features.put(prefix + "_has_3_same_color", (maxColorCount >= 3 ? 1.0 : 0.0));

        /// check for straight;
        List<Integer> patterCardNumbers = BasePatten.convertCardToNumber(card);
        features.put(prefix + "_max_sequence", (double) pattern.getMaxSequence(pattern.getNonColorArray(), patterCardNumbers));

        features.put(prefix + "_max_sequence_color", (double) pattern.getMaxSequence(pattern.getColorArray(card.getColor()), patterCardNumbers));
    }


    private static void calculateFeatureForTwoCardWithOpenCards(List<Card> ownerCards, List<Card> openCards, Map<String, Double> features) {
        FeaturePattern featurePattern = new FeaturePattern(openCards);

        if (ownerCards.get(0).getNumber().equals(ownerCards.get(1).getNumber())) {
            // 持有一对。
            int openCardsWithSameNumbers = featurePattern.getSameCardNumber(ownerCards.get(0));
            features.put("open_cards_has_3_same_cards", openCardsWithSameNumbers > 0 ? 1.0 : 0.0);
            features.put("open_cards_has_4_same_cards", openCardsWithSameNumbers > 1 ? 1.0 : 0.0);
        } else {
            features.put("open_cards_has_3_same_cards", -999999.0);
            features.put("open_cards_has_4_same_cards", -999999.0);
        }

        Set<Integer> cardNumbers = new HashSet<>();
        cardNumbers.addAll(BasePatten.convertCardToNumber(ownerCards.get(0)));
        cardNumbers.addAll(BasePatten.convertCardToNumber(ownerCards.get(1)));

        features.put("open_cards_with_owner_all_max_sequence", (double) featurePattern.getMaxSequence(featurePattern.getNonColorArray(), new ArrayList<>(cardNumbers)));

        if (ownerCards.get(0).getColor().equals(ownerCards.get(1).getColor())) {
            Color givenColor = ownerCards.get(0).getColor();
            features.put("open_cards_with_owner_color_max_sequence", (double) featurePattern.getMaxSequence(featurePattern.getColorArray(givenColor), new ArrayList<>(cardNumbers)));

            int maxColorCount = 0;
            for (Color eachColor : Color.values()) {
                int openCardColorCount = featurePattern.getColorNumber(eachColor);
                if (eachColor.equals(givenColor)) {
                    maxColorCount = Math.max(maxColorCount, openCardColorCount + 2);
                }
            }

            features.put("open_cards_with_owner_max_colors", (double) maxColorCount);
        } else {
            features.put("open_cards_with_owner_max_colors", -999999.0);
            features.put("open_cards_with_owner_color_max_sequence", -999999.0);
        }
    }

    public static Map<String, Double> generateFeatures(List<Card> playCards, List<Card> openCards) {
        Map<String, Double> features = new HashMap<>();

        calculateBaseFeature(playCards, features);
        calculateFeatureForOneCardWithOpenCards(playCards, openCards, features);
        calculateFeatureForTwoCardWithOpenCards(playCards, openCards, features);


        List<Card> allCards = new ArrayList<>();
        allCards.addAll(playCards);
        allCards.addAll(openCards);

        BasePatten patten = PatternBuilder.findPattern(allCards);
        features.put("level", (double) patten.getLevel());
        return features;
    }

}
