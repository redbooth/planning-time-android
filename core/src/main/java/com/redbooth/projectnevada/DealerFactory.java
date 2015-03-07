package com.redbooth.projectnevada;

import java.util.ArrayList;
import java.util.List;

public class DealerFactory {
    public static Dealer getInstance() {
        return new Dealer(getCards());
    }

    private static List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            cards.add(new Card(cardType));
        }
        return cards;
    }
}
