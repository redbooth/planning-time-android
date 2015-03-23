package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redbooth.projectnevada.model.CardModel;
import com.redbooth.projectnevada.widgets.CardView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardFragment extends Fragment {
    @InjectView(R.id.card) CardView cardView;
    private CardModel intialModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_card, container, false);
       ButterKnife.inject(this, view);
       cardView.setCard(intialModel);
       return view;
    }

    public void setCard(CardModel cardModel) {
        if (cardView != null) {
            cardView.setCard(cardModel);
        } else {
            intialModel = cardModel;
        }
    }
}
