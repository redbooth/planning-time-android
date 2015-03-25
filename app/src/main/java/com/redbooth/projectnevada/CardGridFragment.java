package com.redbooth.projectnevada;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;

import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.core.DealerFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class CardGridFragment extends Fragment {

    private final View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int radius = (int) Math.hypot(right, bottom);
                Animator reveal = ViewAnimationUtils.createCircularReveal(v, right, bottom, 0, radius);
                reveal.setDuration(500);
                reveal.start();
            }
        }
    };

    @InjectView(R.id.list) GridView list;

    public static CardGridFragment newInstance() {
        return new CardGridFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        view.addOnLayoutChangeListener(onLayoutChangeListener);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Dealer dealer = DealerFactory.newInstance();
        CardsGridAdapter adapter = new CardsGridAdapter(getActivity(), dealer);
        list.setAdapter(adapter);
    }

    @OnItemClick(R.id.list) @SuppressWarnings("unused")
    public void onItemClick(int position) {
        ((MainActivity)getActivity()).selectCard(position);
        ((MainActivity)getActivity()).showListFragment();
    }
}
