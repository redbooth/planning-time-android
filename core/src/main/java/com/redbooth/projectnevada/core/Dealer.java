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
package com.redbooth.projectnevada.core;

import java.util.List;

public class Dealer {
    private final List<Card> deck;
    private DeckStatus deckStatus;

    Dealer(List<Card> deck) {
        this.deck = deck;
        this.deckStatus = DeckStatus.UPWARDS;
    }

    public int getDeckLength() {
        return deck.size();
    }

    public DeckStatus getDeckStatus() {
        return deckStatus;
    }

    public void flipDeck() {
        switch (deckStatus) {
            case UPWARDS:
                deckStatus = DeckStatus.DOWNWARDS;
                break;
            case DOWNWARDS:
                deckStatus = DeckStatus.UPWARDS;
                break;
        }
    }

    public Card getCardAtPosition(int position) {
        return deck.get(position);
    }

    public enum DeckStatus {
        UPWARDS,
        DOWNWARDS
    }

}
