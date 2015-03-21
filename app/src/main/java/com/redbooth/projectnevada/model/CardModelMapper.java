package com.redbooth.projectnevada.model;

import com.redbooth.projectnevada.core.Card;
import com.redbooth.projectnevada.R;

public class CardModelMapper {
    public CardModel map(Card card) {
        return new CardModel.Builder()
                .setDownwardResourceId(R.drawable.card_downward)
                .setUpwardResourceId(R.drawable.card_upward_ping_pong)
                .build();
    }
}
