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
    private static final String KEY_INITIAL_MODEL = "com.redbooth.projectnevada.KEY_INITIAL_MODEL";

    public interface OnCardStatusChangeListener {
        void onCardStatusChange(Fragment fragment, CardViewModel card,
                                CardViewModel.CardStatus newStatus);
    }

    @InjectView(R.id.card) CardView cardView;
    private CardViewModel storedModel;
    private OnCardStatusChangeListener listener;
    private CardView.OnCardStatusChangeListener cardStatusChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        cardStatusChangeListener = new CardView.OnCardStatusChangeListener() {
            @Override
            public void onCardStatusChange(CardView view, CardViewModel card,
                                           CardViewModel.CardStatus newStatus) {
                if (listener != null) {
                    listener.onCardStatusChange(CardFragment.this, card, newStatus);
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_card, container, false);
       ButterKnife.inject(this, view);
       configureCardView(savedInstanceState);
       return view;
    }

    private void configureCardView(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_INITIAL_MODEL)) {
            storedModel = (CardViewModel) savedInstanceState.getSerializable(KEY_INITIAL_MODEL);
        }
        if (storedModel == null) {
            storedModel = new CardViewModel();
        }
        cardView.setCard(storedModel);
        cardView.setOnCardViewStatusChangeListener(cardStatusChangeListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_INITIAL_MODEL, storedModel);
        super.onSaveInstanceState(outState);
    }

    public void setOnCardStatusChangeListener(OnCardStatusChangeListener listener) {
        this.listener = listener;
    }

    public void setCardStatus(CardViewModel.CardStatus status) {
        if (cardView != null) {
            if (status == CardViewModel.CardStatus.DOWNWARDS) {
                cardView.hideCard();
            } else {
                cardView.revealCard();
            }
        } else {
            if (storedModel != null) {
                storedModel.setStatus(status);
            }
        }
    }

    public void setCard(CardViewModel cardViewModel) {
        if (cardView != null) {
            cardView.setCard(cardViewModel);
        } else {
            storedModel = cardViewModel;
        }
    }
}
