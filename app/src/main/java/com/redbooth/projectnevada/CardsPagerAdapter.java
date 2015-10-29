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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.redbooth.projectnevada.core.Card;
import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.model.CardViewModel;

import java.util.ArrayList;
import java.util.List;

public class CardsPagerAdapter extends FragmentPagerAdapter {
    private final Dealer dealer;
    private final List<CardFragment> fragmentList;
    private final List<CardViewModel> cardViewModelList;
    private CardFragment.OnCardStatusChangeListener onCardFragmentStateChange;

    public CardsPagerAdapter(FragmentManager fragmentManager, Dealer dealer) {
        super(fragmentManager);
        this.dealer = dealer;
        this.fragmentList = new ArrayList<>(dealer.getDeckLength());
        this.cardViewModelList = new ArrayList<>(dealer.getDeckLength());
        initializeListener();
        initializeFragmentPool();
        initializeCardModelPool();
    }

    private void initializeListener() {
        onCardFragmentStateChange = new CardFragment.OnCardStatusChangeListener() {
            @Override
            public void onCardStatusChange(Fragment fragment, CardViewModel card, CardViewModel.CardStatus newStatus) {
                for(int index = 0; index < cardViewModelList.size(); index++) {
                    cardViewModelList.get(index).setStatus(newStatus);
                    fragmentList.get(index).setCardStatus(newStatus);
                    dealer.flipDeck();
                }
            }
        };
    }

    private void initializeFragmentPool() {
        int count = dealer.getDeckLength();
        for(int index = 0; index < count; index++) {
            CardFragment fragment = new CardFragment();
            fragment.setOnCardStatusChangeListener(onCardFragmentStateChange);
            fragmentList.add(fragment);
        }
    }

    private void initializeCardModelPool() {
        int count = dealer.getDeckLength();
        for(int index = 0; index < count; index++) {
            CardViewModel cardViewModel = new CardViewModel();
            cardViewModel.setDownwardResourceId(R.drawable.cover_big);
            cardViewModel.setUpwardResourceId(R.drawable.card14_big);
            CardViewModel.CardStatus cardStatus = CardViewModel.CardStatus.UPWARDS;
            if (dealer.getDeckStatus() == Dealer.DeckStatus.DOWNWARDS) {
                cardStatus = CardViewModel.CardStatus.DOWNWARDS;
            }
            cardViewModel.setStatus(cardStatus);
            cardViewModelList.add(cardViewModel);
        }
    }

    @Override
    public Fragment getItem(int position) {
        CardFragment fragment = fragmentList.get(position);
        CardViewModel cardViewModel = cardViewModelList.get(position);
        cardViewModel.setUpwardResourceId(getUpwardResourceId(position));
        fragment.setCard(cardViewModel);
        return fragment;
    }

    private int getUpwardResourceId(int position) {
        Card card = dealer.getCardAtPosition(position);
        int upwardResourceId = R.drawable.cover_big;
        switch (card) {
            case ONE:
                upwardResourceId = R.drawable.card01_big;
                break;
            case TWO:
                upwardResourceId = R.drawable.card02_big;
                break;
            case THREE:
                upwardResourceId = R.drawable.card03_big;
                break;
            case FIVE:
                upwardResourceId = R.drawable.card04_big;
                break;
            case EIGHT:
                upwardResourceId = R.drawable.card05_big;
                break;
            case THIRTEEN:
                upwardResourceId = R.drawable.card06_big;
                break;
            case TWENTY:
                upwardResourceId = R.drawable.card07_big;
                break;
            case FORTY:
                upwardResourceId = R.drawable.card08_big;
                break;
            case HUNDRED:
                upwardResourceId = R.drawable.card09_big;
                break;
            case INFINITE:
                upwardResourceId = R.drawable.card10_big;
                break;
            case UNKNOWN:
                upwardResourceId = R.drawable.card11_big;
                break;
            case YAK_SHAVING:
                upwardResourceId = R.drawable.card12_big;
                break;
            case BROWN:
                upwardResourceId = R.drawable.card13_big;
                break;
            case PAUSE:
                upwardResourceId = R.drawable.card14_big;
                break;
        }
        return upwardResourceId;
    }

    @Override
    public int getCount() {
        return dealer.getDeckLength();
    }
}
