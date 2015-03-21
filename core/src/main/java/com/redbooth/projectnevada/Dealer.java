package com.redbooth.projectnevada;

import java.util.List;

public class Dealer {
    private final List<Card> deck;
    private DeckStatus deckStatus;
    private int selectedCardPosition;

    public Dealer(List<Card> deck, int position) {
        this.deck = deck;
        this.selectedCardPosition = position;
        this.deckStatus = DeckStatus.UPWARDS;
    }

    public Card getCard() {
        return deck.get(selectedCardPosition);
    }

    public Card getNext() {
        return deck.get(getNextPosition());
    }

    private int getNextPosition() {
        return (selectedCardPosition + 1) % deck.size();
    }

    public void moveNext() {
        selectedCardPosition = getNextPosition();
    }

    public Card getPrevious() {
        return deck.get(getPreviousPosition());
    }

    private int getPreviousPosition() {
        return (selectedCardPosition - 1 + deck.size()) % deck.size();
    }

    public void movePrevious() {
        selectedCardPosition = getPreviousPosition();
    }

    public boolean isLast() {
        return selectedCardPosition == deck.size() - 1;
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

    public enum DeckStatus {
        UPWARDS,
        DOWNWARDS
    }

}
