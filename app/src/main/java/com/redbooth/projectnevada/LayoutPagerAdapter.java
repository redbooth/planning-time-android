package com.redbooth.projectnevada;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class LayoutPagerAdapter extends FragmentStatePagerAdapter {

    Dealer dealer;

    public LayoutPagerAdapter(FragmentManager fm, Dealer dealer) {
        super(fm);
        this.dealer = dealer;
    }

    @Override
    public Fragment getItem(int position) {
        //TODO

    }

    @Override
    public int getCount() {
        return dealer.getDeckLength();
    }
}
