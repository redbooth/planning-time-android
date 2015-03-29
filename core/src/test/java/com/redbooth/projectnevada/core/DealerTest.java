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