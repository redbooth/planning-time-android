package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.core.DealerFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CardListFragment extends Fragment {
    @InjectView(R.id.pager) ViewPager viewPager;
    @InjectView(R.id.floating) FloatingActionButton floatingButton;
    private CardsPagerAdapter cardsPagerAdapter;

    public static CardListFragment newInstance() {
        return new CardListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Dealer dealer = DealerFactory.newInstance();
        this.cardsPagerAdapter = new CardsPagerAdapter(getFragmentManager(), dealer);
        this.viewPager.setAdapter(cardsPagerAdapter);
    }

    @OnClick(R.id.floating)
    protected void onFloatingClick() {
        ((MainActivity)getActivity()).showGridFragment();
    }

    public void selectCard(int position) {
        viewPager.setCurrentItem(position, true);
    }
}
