package com.card.patten;


import com.card.data.Card;
import java.util.*;

public class StraigntFlush extends BasePatten {



    public void apply(List<Card> cardList) {
        super.apply(cardList);

        checkStraintFlushColor(spade);
        checkStraintFlushColor(heart);
        checkStraintFlushColor(diamond);
        checkStraintFlushColor(club);


        if (isFlag()) {
            setLevel(10);
        }
    }

}
