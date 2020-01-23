package com.card;

import com.alibaba.fastjson.JSONObject;
import com.card.data.CompareResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Summary {

    public static void main(String[] args) throws  Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/chenhong/card_play_result.txt")));

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

            if (compareResult == 0) {
                count++;
                if (count < 100) {
                    System.out.println(object.get("openList"));
                    System.out.println(object.get("nextTwoCardsList"));
                    System.out.println(object.get("playA"));
                    System.out.println(object.get("playB"));
                    System.out.println("---end");

                }
            }
            CompareResult result = map.getOrDefault(level, new CompareResult());
            result.addResult(compareResult);

            map.put(level, result);
        }


        for (Map.Entry<Integer, CompareResult> entry : map.entrySet()) {
            int sum = entry.getValue().getWin();
            sum += entry.getValue().getLose();
            sum += entry.getValue().getEqualCount();
            System.out.println(entry.getKey() + " ---> " + sum + " __> " +  entry.getValue().getWin() + " -- " + entry.getValue().getLose() + " ---> " + entry.getValue().getEqualCount());
        }


    }
}
