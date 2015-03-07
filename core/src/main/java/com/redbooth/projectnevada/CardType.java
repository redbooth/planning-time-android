package com.redbooth.projectnevada;

public enum CardType {
    ONE("Low hanging fruit"),
    TWO("Piece of cake"),
    THREE("???"),
    FIVE("Cinco ornitorrinco"),
    EIGHT("???"),
    THIRTEEN("???"),
    TWENTY("Don't put all the eggs in one basket"),
    FORTY("Berenjenal"),
    HUNDRED("???"),
    INFINITE("When pigs fly"),
    UNKNOWN("Here be dragons"),
    PAUSE("Time for a ping pong"),
    BROWN("It's clearly a brown"),
    YAK_SHAVING("Yak shaving!");

    private final String legend;

    CardType(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }
}