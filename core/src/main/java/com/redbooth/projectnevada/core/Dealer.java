package com.redbooth.projectnevada.core;

import java.util.List;

public class Dealer {
    private final List<Card> deck;
    private DeckStatus deckStatus;

    Dealer(List<Card> deck) {
        this.deck = deck;
        this.deckStatus = DeckStatus.UPWARDS;
    }

    public int getDeckLength() {
        return deck.size();
    }

    public DeckStatus getDeckStatus() {
        return deckStatus;
    }

    public void flipDeck() {
        switch (deckStatus) {
            case UPWARDS:
                deckStatus = DeckStatus.DOWNWARDS;
                break;
            case DOWNWARDS:
                deckStatus = DeckStatus.UPWARDS;
                break;
        }
    }

    public Card getCardAtPosition(int position) {
        return deck.get(position);
    }

    public enum DeckStatus {
        UPWARDS,
        DOWNWARDS
    }

}
