package com.redbooth.projectnevada;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.GridView;

import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.cards_grid) GridView cardsGridView;
    @InjectView(R.id.card_view_stub) ViewStub cardViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardsGridView.setAdapter();
    }
}
