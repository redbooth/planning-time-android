package com.redbooth.projectnevada;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class DealerTest {
    private static final int INITIAL_POSITION = 0;
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
       dealer = new Dealer(cards, INITIAL_POSITION);
    }

    @Test public void shouldReturnTheFirstCard() {
        Card first = dealer.getCard();

        assertThat(first).isEqualTo(FIRST_CARD);
    }

    @Test public void shouldReturnSecondCardCallingNextFromFirstCard() {
        Card secondCard = dealer.getNext();

        assertThat(secondCard).isEqualTo(SECOND_CARD);
    }

    @Test public void shouldReturnFirstCardCallingNextFromLastCard() {
        dealer.moveNext();
        dealer.moveNext();
        Card firstCard = dealer.getNext();

        assertThat(firstCard).isEqualTo(FIRST_CARD);
    }

    @Test public void shouldReturnLastCardCallingPreviousFromFirstCard() {
        Card lastCard = dealer.getPrevious();

        assertThat(lastCard).isEqualTo(THIRD_CARD);
    }

    @Test public void shouldReturnTrueIfIsLastCard() {
        dealer.movePrevious();
        boolean isLast = dealer.isLast();

        assertThat(isLast).isEqualTo(true);
    }

    @Test public void shouldReturnFalseIfNotLastCard() {
        dealer.getNext();
        boolean isLast = dealer.isLast();

        assertThat(isLast).isEqualTo(false);
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