package com.redbooth.projectnevada;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.GridView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity {
    @InjectView(R.id.pager)
    ViewPager viewPager;

    //TODO inject
    LayoutPagerAdapter layoutPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        this.viewPager.setAdapter(layoutPagerAdapter);
    }
}
