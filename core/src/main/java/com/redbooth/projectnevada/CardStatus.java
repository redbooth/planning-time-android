package com.redbooth.projectnevada;

public interface CardStatus {
    public CardStatus nextStatus();
    public CardStatus.Status getStatus();

    public enum Status {
        UPWARDS,
        DOWNWARDS
    }
}
