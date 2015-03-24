package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
    private CardListFragment cardListFragment;
    private CardGridFragment cardGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardListFragment = CardListFragment.newInstance();
        cardGridFragment = CardGridFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_layout, cardListFragment, "CardListFragment")
                .add(R.id.content_layout, cardGridFragment, "CardGridFragment")
                .hide(cardGridFragment)
                .show(cardListFragment)
                .commit();
    }

    public void showGridFragment() {
        getSupportFragmentManager().beginTransaction()
                .show(cardGridFragment)
                .addToBackStack(null)
                .commit();
    }
}
