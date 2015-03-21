package com.redbooth.projectnevada;

import java.util.Arrays;
import java.util.List;

public class DealerFactory {
    public static Dealer newInstance() {
        return new Dealer(getCards(), 0);
    }

    public static Dealer newInstanceWithPosition(int position) {
        return new Dealer(getCards(), position);
    }

    private static List<Card> getCards() {
        return Arrays.asList(Card.values());
    }
}
