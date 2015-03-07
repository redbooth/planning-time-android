package com.redbooth.projectnevada;

import java.util.List;

public class Dealer {
    private final List<Card> cards;
    private int selectedCardPosition;

    public Dealer(List<Card> cards) {
        this.cards = cards;
        this.selectedCardPosition = 0;
    }

    public Card getCard() {
        return cards.get(selectedCardPosition);
    }

    public Card getNext() {
        selectedCardPosition = (selectedCardPosition + 1) % cards.size();
        return cards.get(selectedCardPosition);
    }

    public Card getPrevious() {
        selectedCardPosition = (selectedCardPosition - 1 + cards.size()) % cards.size();
        return cards.get(selectedCardPosition);
    }

    public boolean isLast() {
        return selectedCardPosition == cards.size() - 1;
    }
}
