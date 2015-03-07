package com.redbooth.projectnevada;

public class Card {
    private Status status;

    public Card() {
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

    public enum Status {
        UPWARDS,
        DOWNWARDS
    }
}
