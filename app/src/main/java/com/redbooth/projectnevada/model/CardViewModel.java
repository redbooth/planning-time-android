package com.redbooth.projectnevada.model;

import com.redbooth.projectnevada.R;

import java.io.Serializable;

public class CardViewModel implements Serializable {
    private int upwardResourceId;
    private int downwardResourceId;
    private CardStatus status;

    public CardViewModel() {
        this.status = CardStatus.UPWARDS;
        this.downwardResourceId = R.drawable.cover_big;
        this.upwardResourceId = R.drawable.card01_big;
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
