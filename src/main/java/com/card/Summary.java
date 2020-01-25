package com.card;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.card.data.Card;
import com.card.data.Color;
import com.card.data.CompareResult;
import com.card.feature.FeatureGenerator;

import java.io.*;
import java.util.*;

public class Summary {

    private static Color convertColor(String v) {
        if ("HEART".equals(v)) {
            return Color.HEART;
        } else if ("SPADE".equals(v)) {
            return Color.SPADE;
        } else if ("DIAMOND".equals(v)) {
            return Color.DIAMOND;
        } else if ("CLUB".equals(v)) {
            return Color.CLUB;
        }
        throw new IllegalStateException("The color " + v + " does not exist");
    }

    public static void main(String[] args) throws  Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/chenhong/card_play_result.txt")));

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/chenhong/card_play_feature_3.csv")));


        boolean isFirst = true;

        Map<Integer, CompareResult> map = new HashMap<>();
        int count = 0;
        while(true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            JSONObject object = JSONObject.parseObject(line);
            int compareResult = object.getInteger("compareResult");
            JSONObject playAPattern = object.getJSONObject("playAPattern");
            int level = playAPattern.getInteger("level");

            if (compareResult != 0) {
                List<Card> playACardList = new ArrayList<>();
                List<Card> openList = new ArrayList<>();
                JSONArray array = object.getJSONArray("playA");
                for (int i = 0; i < array.size(); i++) {
                    JSONObject cardObject = (JSONObject) array.get(i);
                    String number = cardObject.getString("number");

                    Color color = convertColor(cardObject.getString("color"));

                    Card card = new Card();
                    card.setColor(color);
                    card.setNumber(number);
                    playACardList.add(card);
                }

                JSONArray openArray = object.getJSONArray("openList");
                for (int i = 0; i < openArray.size(); i++) {
                    JSONObject cardObject = (JSONObject) openArray.get(i);
                    String number = cardObject.getString("number");

                    Color color = convertColor(cardObject.getString("color"));

                    Card card = new Card();
                    card.setColor(color);
                    card.setNumber(number);
                    openList.add(card);
                }

//                System.out.println(playACardList);
//                System.out.println(openList);

                Map<String, Double> features = FeatureGenerator.generateFeatures(playACardList, openList);

                TreeMap<String, Double> treeMap = new TreeMap<>(features);

                if (isFirst) {
                    isFirst = false;

                    StringBuilder headBuilder = new StringBuilder();
                    headBuilder.append("label");
                    for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
                        headBuilder.append(",").append(entry.getKey());
                    }
                    writer.write(headBuilder.toString());
                    writer.newLine();
                }

                StringBuilder sb = new StringBuilder();
                if (compareResult == -1) {
                    sb.append("0");
                } else {
                    sb.append(compareResult);
                }
                for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
                    sb.append(",").append(entry.getValue());
                }
                writer.write(sb.toString());
                writer.newLine();

                count++;
                if (count > 1000000) {
                    break;
                }
            }
            CompareResult result = map.getOrDefault(level, new CompareResult());
            result.addResult(compareResult);

            map.put(level, result);
        }

        writer.close();


        for (Map.Entry<Integer, CompareResult> entry : map.entrySet()) {
            int sum = entry.getValue().getWin();
            sum += entry.getValue().getLose();
            sum += entry.getValue().getEqualCount();
            System.out.println(entry.getKey() + " ---> " + sum + " __> " +  entry.getValue().getWin() + " -- " + entry.getValue().getLose() + " ---> " + entry.getValue().getEqualCount());
        }


    }
}
