package com.card;

import com.alibaba.fastjson.JSONObject;
import com.card.data.Assignee;
import com.card.data.Card;
import com.card.data.CardGenerator;
import com.card.data.Shuffle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Test {

    public static void main(String[] args) throws Exception {

        List<Card> cardList = CardGenerator.generateCard();

        System.out.println(cardList.size());


       // BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/chenhong/card_play_result.txt")));

        for (int i = 0; i < 5000000; i++) {
            List<Card> shuffled = Shuffle.shuffleCards(cardList);

            Assignee assignee = new Assignee(shuffled);
            int compareResult = assignee.comparePlayer();
            assignee.setCompareResult(compareResult);

            String jsonStr = JSONObject.toJSONString(assignee);
           // writer.write(jsonStr);
           // writer.newLine();

            if (i % 10000 == 0) {
                System.out.println("proces count=" + i);
            }
        }
   //     writer.close();

    }
}
