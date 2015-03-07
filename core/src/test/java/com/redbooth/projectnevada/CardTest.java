package com.redbooth.projectnevada;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class CardTest {
    private Card card;

    @Before public void setup() {
        card = new Card();
    }

    @Test public void shouldBeInitializedUpwards() {
        Card.Status status = card.getStatus();

        assertThat(status).isEqualTo(Card.Status.UPWARDS);
    }

    @Test public void shouldBeDownwardsAfterFirstFlip() {
        card.flip();

        assertThat(card.getStatus()).isEqualTo(Card.Status.DOWNWARDS);
    }

    @Test public void shouldBeUpwardsAfterAFlipFromDownwards() {
        card.flip();
        card.flip();

        assertThat(card.getStatus()).isEqualTo(Card.Status.UPWARDS);
    }
}