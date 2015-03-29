package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redbooth.projectnevada.model.CardViewModel;
import com.redbooth.projectnevada.widgets.CardView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardFragment extends Fragment {
    public interface OnCardStatusChangeListener {
        void onCardStatusChange(Fragment fragment, CardViewModel card, CardViewModel.CardStatus newStatus);
    }

    private CardView.OnCardStatusChangeListener cardStatusChangeListener = new CardView.OnCardStatusChangeListener() {
        @Override
        public void onCardStatusChange(CardView view, CardViewModel card, CardViewModel.CardStatus newStatus) {
            if (listener != null) {
                listener.onCardStatusChange(CardFragment.this, card, newStatus);
            }
        }
    };

    @InjectView(R.id.card) CardView cardView;
    private CardViewModel intialModel;
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

    public void setCardStatus(CardViewModel.CardStatus status) {
        if (cardView != null) {
            if (status == CardViewModel.CardStatus.DOWNWARDS) {
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

    public void setCard(CardViewModel cardViewModel) {
        if (cardView != null) {
            cardView.setCard(cardViewModel);
        } else {
            intialModel = cardViewModel;
        }
    }
}
