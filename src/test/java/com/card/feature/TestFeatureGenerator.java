package com.card.feature;

import com.card.data.Card;
import com.card.data.Color;
import com.card.patten.TestUtil;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.util.*;

public class TestFeatureGenerator {

    @Test
    public void testAlmostFlush_1() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardA(Color.CLUB));
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);

        assert 4 == features.get("open_cards_with_owner_color_max_sequence");

        assert 3 == features.get("first_card_mix_max_color_count");

        assert 0 == features.get("owner_is_pair");

    }
    @Test
    public void testAlmostFlush_2() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        playCard.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 2));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);

        assert 3 == features.get("first_card_mix_max_sequence_color");

        assert 3 == features.get("first_card_mix_max_color_count");

        assert 1 == features.get("owner_is_pair");

    }

    @Test
    public void testAlmostFlush_3() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 5));
        playCard.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 6));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 7));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);
        for (Map.Entry<String, Double> entry : features.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        assert 5 == features.get("open_cards_with_owner_all_max_sequence");

    }

    @Test
    public void testHasPair_1() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        playCard.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 4));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);


        assert 3 == features.get("first_card_mix_max_color_count");

        assert 1 == features.get("second_card_mix_has_pair");

        System.out.println(features);
    }

    @Test
    public void testHasPair_2() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 2));
        playCard.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 9));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 10));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);


        assert 3 == features.get("first_card_mix_max_color_count");

        assert 0.0 == features.get("second_card_mix_has_pair");

        System.out.println(features);
    }

    @Test
    public void testHasFourCards() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        playCard.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 9));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.SPADE, 9));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 10));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);

        for (Map.Entry<String, Double> entry : features.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        assert 1.0 == features.get("open_cards_has_3_same_cards");
    }

    @Test
    public void testHasFourCards_2() {

        List<Card> playCard = new ArrayList<Card>();
        playCard.add(TestUtil.createCardNormalNumber(Color.CLUB, 9));
        playCard.add(TestUtil.createCardNormalNumber(Color.DIAMOND, 9));

        List<Card> openCards = new ArrayList<Card>();
        openCards.add(TestUtil.createCardNormalNumber(Color.SPADE, 9));
        openCards.add(TestUtil.createCardNormalNumber(Color.HEART, 9));
        openCards.add(TestUtil.createCardNormalNumber(Color.CLUB, 3));

        Map<String, Double> features = FeatureGenerator.generateFeatures(playCard, openCards);

        for (Map.Entry<String, Double> entry : features.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        assert 1.0 == features.get("open_cards_has_4_same_cards");
    }


}
