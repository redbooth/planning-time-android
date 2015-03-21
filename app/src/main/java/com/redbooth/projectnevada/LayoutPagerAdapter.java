package com.redbooth.projectnevada;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.redbooth.projectnevada.core.Dealer;

public class LayoutPagerAdapter extends FragmentStatePagerAdapter {

    Dealer dealer;

    public LayoutPagerAdapter(FragmentManager fm, Dealer dealer) {
        super(fm);
        this.dealer = dealer;
    }

    @Override
    public Fragment getItem(int position) {
        //TODO
        return null;
    }

    @Override
    public int getCount() {
        return dealer.getDeckLength();
    }
}
