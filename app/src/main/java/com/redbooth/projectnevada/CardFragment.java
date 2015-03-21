package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardFragment extends Fragment implements CardView {
    
    @InjectView(R.id.card_image)
    ImageView cardImage;

    //TODO inject
    CardPresenter presenter;

    private View cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       cardView = inflater.inflate(R.layout.card_fragment, container, false);
       ButterKnife.inject(this,cardView);
       return cardView;
    }

    @Override
    public void renderCard() {

    }

    @Override
    public void renderFlip() {
        //TODO animation(?)
    }
}
