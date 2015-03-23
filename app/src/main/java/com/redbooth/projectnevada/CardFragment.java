package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redbooth.projectnevada.model.CardModel;
import com.redbooth.projectnevada.widgets.CardView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardFragment extends Fragment {
    public interface OnCardStatusChangeListener {
        void onCardStatusChange(Fragment fragment, CardModel card, CardModel.CardStatus newStatus);
    }

    private CardView.OnCardStatusChangeListener cardStatusChangeListener = new CardView.OnCardStatusChangeListener() {
        @Override
        public void onCardStatusChange(CardView view, CardModel card, CardModel.CardStatus newStatus) {
            if (listener != null) {
                listener.onCardStatusChange(CardFragment.this, card, newStatus);
            }
        }
    };

    @InjectView(R.id.card) CardView cardView;
    private CardModel intialModel;
    private OnCardStatusChangeListener listener;

    public void setOnCardStatusChangeListener(OnCardStatusChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_card, container, false);
       ButterKnife.inject(this, view);
       cardView.setCard(intialModel);
       cardView.setOnCardViewStatusChangeListener(cardStatusChangeListener);
       return view;
    }

    public void setCardStatus(CardModel.CardStatus status) {
        if (cardView != null) {
            if (status == CardModel.CardStatus.DOWNWARDS) {
                cardView.hideCard();
            } else {
                cardView.revealCard();
            }
        } else {
            if (intialModel != null) {
                intialModel.setStatus(status);
            }
        }
    }

    public void setCard(CardModel cardModel) {
        if (cardView != null) {
            cardView.setCard(cardModel);
        } else {
            intialModel = cardModel;
        }
    }
}
