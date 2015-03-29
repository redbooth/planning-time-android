package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {
    private static final String TAG_CARD_LIST_FRAGMENT = "CardListFragment";
    private static final String TAG_CARD_GRID_FRAGMENT = "CardGridFragment";
    private CardListFragment cardListFragment;
    private CardGridFragment cardGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFragments();
    }

    private void initializeFragments() {
        initializeGridFragment();
        initializeListFragment();
    }

    private void initializeListFragment() {
        cardListFragment = (CardListFragment)
                getSupportFragmentManager().findFragmentByTag(TAG_CARD_LIST_FRAGMENT);
        if (cardListFragment == null) {
            cardListFragment = CardListFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_layout, cardListFragment, TAG_CARD_LIST_FRAGMENT)
                    .show(cardListFragment)
                    .commit();
        }
    }

    private void initializeGridFragment() {
        cardGridFragment = (CardGridFragment)
                getSupportFragmentManager().findFragmentByTag(TAG_CARD_GRID_FRAGMENT);
        if (cardGridFragment == null) {
            cardGridFragment = CardGridFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_layout, cardGridFragment, TAG_CARD_GRID_FRAGMENT)
                    .hide(cardGridFragment)
                    .commit();
        }
    }

    public void selectCard(int position) {
        cardListFragment.selectCard(position);
    }

    public void showListFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public void showGridFragment() {
        getSupportFragmentManager().beginTransaction()
                .show(cardGridFragment)
                .addToBackStack(null)
                .commit();
    }
}
