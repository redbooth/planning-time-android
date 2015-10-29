/*
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.core.DealerFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CardListFragment extends Fragment {
    @InjectView(R.id.pager) ViewPager viewPager;

    public static CardListFragment newInstance() {
        return new CardListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Dealer dealer = DealerFactory.newInstance();
        CardsPagerAdapter cardsPagerAdapter = new CardsPagerAdapter(getFragmentManager(), dealer);
        this.viewPager.setAdapter(cardsPagerAdapter);
    }

    @OnClick(R.id.floating) @SuppressWarnings("unused")
    protected void onFloatingClick() {
        ((MainActivity)getActivity()).showGridFragment();
    }

    public void selectCard(int position) {
        viewPager.setCurrentItem(position, true);
    }
}
