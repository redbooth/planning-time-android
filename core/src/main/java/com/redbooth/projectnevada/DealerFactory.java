package com.redbooth.projectnevada;

import java.util.Arrays;
import java.util.List;

public class DealerFactory {
    public static Dealer getInstance() {
        return new Dealer(getCards(), 0);
    }

    private static List<Card> getCards() {
        return Arrays.asList(Card.values());
    }
}
