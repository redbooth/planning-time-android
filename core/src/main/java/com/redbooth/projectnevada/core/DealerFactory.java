package com.redbooth.projectnevada.core;

import java.util.Arrays;
import java.util.List;

public class DealerFactory {
    public static Dealer newInstance() {
        return new Dealer(getCards());
    }

    private static List<Card> getCards() {
        return Arrays.asList(Card.values());
    }
}
