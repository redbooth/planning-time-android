package com.redbooth.projectnevada;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.core.DealerFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity {
    @InjectView(R.id.pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Dealer dealer = DealerFactory.newInstance();
        CardsPagerAdapter cardsPagerAdapter = new CardsPagerAdapter(getSupportFragmentManager(), dealer);
        this.viewPager.setAdapter(cardsPagerAdapter);
    }
}
