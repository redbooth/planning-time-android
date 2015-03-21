package com.redbooth.projectnevada.model;

public class CardModel {
    private int upwardResourceId;
    private int downwardResourceId;
    private CardStatus status;

    public int getDownwardResourceId() { return downwardResourceId; }
    public int getUpwardResourceId() { return upwardResourceId; }
    public CardStatus getStatus() { return status; }

    public static class Builder {
        private CardModel card;

        public Builder setDownwardResourceId(int resourceId) {
            card.downwardResourceId = resourceId;
            return this;
        }

        public Builder setUpwardResourceId(int resourceId) {
            card.upwardResourceId = resourceId;
            return this;
        }

        public Builder setStatus(CardStatus status) {
            card.status = status;
            return this;
        }

        public Builder() {
            card = new CardModel();
        }

        public CardModel build() {
            return card;
        }
    }

    public enum CardStatus {
        UPWARDS,
        DOWNWARDS
    }
}
