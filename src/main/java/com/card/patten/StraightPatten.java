package com.card.patten;


import com.card.data.Card;

import java.util.List;

public class StraightPatten  extends BasePatten {

    public void apply(List<Card> cardList) {
        super.apply(cardList);

      checkStraintFlushColor(noColorSet);
      if (isFlag()) {
          setLevel(6);
      }
    }
}
