package com.redbooth.projectnevada;

public class Card {
    private final CardType cardType;
    private Status status;

    public Card(CardType cardType) {
        this.cardType = cardType;
        this.status = Status.UPWARDS;
    }

    public Status getStatus() {
        return status;
    }

    public void flip() {
        switch (status) {
            case UPWARDS:
                status = Status.DOWNWARDS;
                break;
            case DOWNWARDS:
                status = Status.UPWARDS;
                break;
        }
    }

    public CardType getCardType() {
        return cardType;
    }

    public enum Status {
        UPWARDS,
        DOWNWARDS
    }
}
