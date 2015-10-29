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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class DealerTest {
    private static final Card FIRST_CARD = Card.BROWN;
    private static final Card SECOND_CARD = Card.UNKNOWN;
    private static final Card THIRD_CARD = Card.INFINITE;
    private Dealer dealer;
    private static final List<Card> cards = new ArrayList<>(3);
    static {
        cards.add(FIRST_CARD);
        cards.add(SECOND_CARD);
        cards.add(THIRD_CARD);
    }

    @Before
    public void setup() {
       dealer = new Dealer(cards);
    }

    @Test public void shouldReturnDeckLength() {
        int length = dealer.getDeckLength();

        assertThat(length).isEqualTo(3);
    }

    @Test public void shouldBeInitializedUpwards() {
        Dealer.DeckStatus status = dealer.getDeckStatus();

        assertThat(status).isEqualTo(Dealer.DeckStatus.UPWARDS);
    }

    @Test public void shouldBeDownwardsAfterFirstFlip() {
        dealer.flipDeck();

        assertThat(dealer.getDeckStatus()).isEqualTo(Dealer.DeckStatus.DOWNWARDS);
    }

    @Test public void shouldBeUpwardsAfterAFlipFromDownwards() {
        dealer.flipDeck();
        dealer.flipDeck();

        assertThat(dealer.getDeckStatus()).isEqualTo(Dealer.DeckStatus.UPWARDS);
    }
}