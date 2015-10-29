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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.redbooth.projectnevada.core.Card;
import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.model.CardViewModel;

import java.util.ArrayList;
import java.util.List;

public class CardsGridAdapter extends BaseAdapter {
    private final Context context;
    private final Dealer dealer;
    private final List<CardViewModel> cardViewModelList;

    public CardsGridAdapter(Context context, Dealer dealer) {
        this.context = context;
        this.dealer = dealer;
        cardViewModelList = new ArrayList<>();
        initializeCardModelPool();
    }

    private void initializeCardModelPool() {
        int count = dealer.getDeckLength();
        for(int index = 0; index < count; index++) {
            int upwardResourceId = getUpwardResourceId(index);
            CardViewModel cardViewModel = new CardViewModel();
            cardViewModel.setDownwardResourceId(R.drawable.cover_big);
            cardViewModel.setUpwardResourceId(upwardResourceId);
            cardViewModel.setStatus(CardViewModel.CardStatus.UPWARDS);
            cardViewModelList.add(cardViewModel);
        }
    }

    @Override
    public int getCount() {
        return dealer.getDeckLength();
    }

    @Override
    public Object getItem(int position) {
        return dealer.getCardAtPosition(position);
    }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater
                            .from(context)
                                .inflate(R.layout.item_small_card, null);
        }
        CardViewModel card = cardViewModelList.get(position);
        ImageView cardView = (ImageView)result.findViewById(R.id.card);
        cardView.setImageResource(card.getUpwardResourceId());
        result.setTag(card);
        return result;
    }

    private int getUpwardResourceId(int position) {
        Card card = dealer.getCardAtPosition(position);
        int upwardResourceId = R.drawable.cover_big;
        switch (card) {
            case ONE:
                upwardResourceId = R.drawable.card01_small;
                break;
            case TWO:
                upwardResourceId = R.drawable.card02_small;
                break;
            case THREE:
                upwardResourceId = R.drawable.card03_small;
                break;
            case FIVE:
                upwardResourceId = R.drawable.card04_small;
                break;
            case EIGHT:
                upwardResourceId = R.drawable.card05_small;
                break;
            case THIRTEEN:
                upwardResourceId = R.drawable.card06_small;
                break;
            case TWENTY:
                upwardResourceId = R.drawable.card07_small;
                break;
            case FORTY:
                upwardResourceId = R.drawable.card08_small;
                break;
            case HUNDRED:
                upwardResourceId = R.drawable.card09_small;
                break;
            case INFINITE:
                upwardResourceId = R.drawable.card10_small;
                break;
            case UNKNOWN:
                upwardResourceId = R.drawable.card11_small;
                break;
            case YAK_SHAVING:
                upwardResourceId = R.drawable.card12_small;
                break;
            case BROWN:
                upwardResourceId = R.drawable.card13_small;
                break;
            case PAUSE:
                upwardResourceId = R.drawable.card14_small;
                break;
        }
        return upwardResourceId;
    }
}
