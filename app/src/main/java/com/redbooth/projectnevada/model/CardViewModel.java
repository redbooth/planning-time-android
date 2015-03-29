package com.redbooth.projectnevada.model;

import java.io.Serializable;

public class CardViewModel implements Serializable {
    private int upwardResourceId;
    private int downwardResourceId;
    private CardStatus status;

    public CardViewModel() {
        this.status = CardStatus.UPWARDS;
    }

    public int getDownwardResourceId() { return downwardResourceId; }

    public int getUpwardResourceId() { return upwardResourceId; }

    public CardStatus getStatus() { return status; }

    public void setUpwardResourceId(int upwardResourceId) {
        this.upwardResourceId = upwardResourceId;
    }

    public void setDownwardResourceId(int downwardResourceId) {
        this.downwardResourceId = downwardResourceId;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public enum CardStatus {
        UPWARDS,
        DOWNWARDS
    }
}
