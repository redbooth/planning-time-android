package com.redbooth.projectnevada;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.redbooth.projectnevada.model.CardModel;


public class CardActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_demo_layout);

        CardModel card = new CardModel.Builder()
                                .setUpwardResourceId(R.drawable.card_upward_ping_pong)
                                .setDownwardResourceId(R.drawable.card_downward)
                                .setStatus(CardModel.CardStatus.UPWARDS)
                                .build();
        final com.redbooth.projectnevada.widgets.CardView cardView
                            = (com.redbooth.projectnevada.widgets.CardView)findViewById(R.id.card);
        cardView.setCard(card);
    }
}
